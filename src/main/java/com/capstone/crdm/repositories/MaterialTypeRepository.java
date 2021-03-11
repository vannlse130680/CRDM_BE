package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.MaterialTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends CrudRepository<MaterialTypeEntity, Integer> {

}
