package com.capstone.crdm.services;

import com.capstone.crdm.constants.EntityStatus;
import com.capstone.crdm.constants.OperationMode;
import com.capstone.crdm.entities.CrdmEntity;
import com.capstone.crdm.exception.CrdmIllegalArgumentException;
import com.capstone.crdm.exception.CrdmResourceNotFoundException;
import com.capstone.crdm.model.QueryModel;
import com.capstone.crdm.repositories.CrdmRepository;
import com.capstone.crdm.utilities.CrdmArgumentParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tennaito.rsql.jpa.JpaPredicateVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public abstract class CrdmService<E extends CrdmEntity<ID>, ID, R extends CrdmRepository<E, ID>> {

    protected abstract R getRepository();

    protected final Class<E> entityClass;

    private Map<String, String> errorMap;

    @Value("${crdm.error-resolution.error-mapping.mapping-file-name:error-mapper.json}")
    private String errorMapperFileName;

    @Autowired
    protected EntityManager entityManager;

    private String cachedEntityName;

    protected static Map<Integer, Sort.Direction> DIRECTION_MAP = new HashMap<>();

    static {
        DIRECTION_MAP.put(1, Sort.Direction.ASC);
        DIRECTION_MAP.put(-1, Sort.Direction.DESC);
    }

    protected CrdmService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected String getEntityName() {
        if (this.cachedEntityName == null) {
            var className = entityClass.getSimpleName();
            this.cachedEntityName = className.endsWith("Entity") ? className.substring(0, className.lastIndexOf("Entity")) : className;
        }
        return this.cachedEntityName;
    }

    protected void validate(E entity, OperationMode operationMode) {

    }

    protected void completeEntity(E entity, OperationMode operationMode) {
        entity.setStatus(EntityStatus.ACTIVE);
    }

    public E create(E entity) {
        entity.setId(null);

        this.validate(entity, OperationMode.CREATE);
        this.completeEntity(entity, OperationMode.CREATE);

        return this.getRepository().save(entity);
    }

    public E update(E entity) {
        if (entity.getId() == null) {
            throw new CrdmIllegalArgumentException("ID must be specified");
        }

        this.validate(entity, OperationMode.UPDATE);

        var savedEntity = this.findById(entity.getId());
        BeanUtils.copyProperties(entity, savedEntity, "id", "createdAt", "createdBy");
        return this.getRepository().save(entity);
    }

    public void delete(ID id) {
        var entity = this.findById(id);
        entity.setStatus(EntityStatus.DELETED);
        this.getRepository().save(entity);
    }

    public E findById(ID id) {
        return this.getRepository().findById(id).orElseThrow(() -> new CrdmResourceNotFoundException(this.getEntityName() + " with ID [" + id + "] cannot be found"));
    }

    public List<E> findAll() {
        return this.getRepository().findAll();
    }

    @SuppressWarnings("unchecked")
    public Page<E> findByQuery(QueryModel queryModel) {
        // validate request model
        if (queryModel == null) {
            throw new CrdmIllegalArgumentException("Query model must be defined");
        }

        // prepare search specification
        Specification<E> searchSpecification = (root, criteriaQuery, criteriaBuilder) -> {
            if (queryModel.getQuery() == null || queryModel.getQuery().isBlank()) {
                return null;
            }

            JpaPredicateVisitor<E> predicateVisitor = new JpaPredicateVisitor<E>().defineRoot(root);
            predicateVisitor.getBuilderTools().setArgumentParser(new CrdmArgumentParser());
            return new RSQLParser().parse(queryModel.getQuery()).accept(predicateVisitor, this.entityManager);
        };

        // prepare sort direction
        List<Sort.Order> orders = new ArrayList<>();
        if (queryModel.getSorts() != null) {
            queryModel.getSorts().forEach((sortField, sortDirection) -> orders.add(new Sort.Order(DIRECTION_MAP.get(sortDirection), sortField)));
        }

        // prepare sort
        Sort sort = Sort.by(orders);

        // prepare page request
        Pageable pageRequest;
        if (queryModel.getPage() != null) {
            if (queryModel.getSize() == null || queryModel.getSize() < 1) {
                queryModel.setSize(20);
            }
            pageRequest = PageRequest.of(queryModel.getPage(), queryModel.getSize(), sort);
        } else {
            pageRequest = Pageable.unpaged();
        }

        // start searching and then return
        var results = this.getRepository().findAll(searchSpecification, pageRequest);

        this.completeResults(results);

        return results;
    }

    protected void completeResults(Page<E> results) {

    }

    public void resolveDataIntegrityViolationException(DataIntegrityViolationException ex, E entity) throws CrdmIllegalArgumentException {
        this.prepareErrorResolver();
        var rootCause = ((ConstraintViolationException) ex.getCause());
        var finalMessage = this.errorMap.getOrDefault(rootCause.getConstraintName(), "Data validation failed. Detailed: " + rootCause.getLocalizedMessage());
        throw new CrdmIllegalArgumentException(finalMessage);
    }

    private void prepareErrorResolver() {
        if (this.errorMap != null) {
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            this.errorMap = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream(this.errorMapperFileName), new TypeReference<>() { });
        } catch (Exception ex) {
            log.warn("Cannot prepare error mapper. Detailed: {}", ex.getLocalizedMessage(), ex);
            this.errorMap = new HashMap<>();
        }
    }

}
