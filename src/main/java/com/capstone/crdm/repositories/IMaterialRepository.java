package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMaterialRepository extends CrudRepository<Material, Integer> {
}
