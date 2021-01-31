package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.ProjectAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectAssignRepository extends CrudRepository<ProjectAssign, Integer> {
    @Override
    <S extends ProjectAssign> Iterable<S> saveAll(Iterable<S> iterable);
}
