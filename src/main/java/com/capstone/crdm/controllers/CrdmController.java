package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.CrdmEntity;
import com.capstone.crdm.model.QueryModel;
import com.capstone.crdm.repositories.CrdmRepository;
import com.capstone.crdm.services.CrdmService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
@RestController
public abstract class CrdmController<E extends CrdmEntity<ID>, ID, R extends CrdmRepository<E, ID>> {

    protected abstract CrdmService<E, ID, R> getService();

    private String getUriPrefix() {
        var annotation = this.getClass().getAnnotation(RequestMapping.class);
        return annotation.path()[0];
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> getOptions() {
        return ResponseEntity.noContent()
                .cacheControl(CacheControl.noCache())
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "false")
                .build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody E entity) {
        try {
            E createdEntity = this.getService().create(entity);
            return ResponseEntity.created(URI.create(this.getUriPrefix() + "/" + createdEntity.getId())).build();
        } catch (DataIntegrityViolationException ex) {
            this.getService().resolveDataIntegrityViolationException(ex, entity);
            return null;
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity) {
        entity.setId(id);
        try {
            this.getService().update(entity);
        } catch (DataIntegrityViolationException ex) {
            this.getService().resolveDataIntegrityViolationException(ex, entity);
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Page<E>> delete(@PathVariable ID id) {
        this.getService().delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable ID id) {
        return ResponseEntity.ok(this.getService().findById(id));
    }

    @GetMapping
    public ResponseEntity<List<E>> findAll() {
        return ResponseEntity.ok(this.getService().findAll());
    }

    @PostMapping(path = "/find-by-query")
    public ResponseEntity<Page<E>> findByQuery(@RequestBody QueryModel queryModel) {
        return ResponseEntity.ok(this.getService().findByQuery(queryModel));
    }

}
