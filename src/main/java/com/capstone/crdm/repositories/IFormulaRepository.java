package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.Formula;
import com.capstone.crdm.entities.ProjectAssign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormulaRepository extends CrudRepository<Formula, Integer> {

    List<Formula> findByProjectId(int projectId);
}
