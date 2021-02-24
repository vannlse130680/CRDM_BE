package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.Formula;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormulaRepository extends CrudRepository<Formula, Integer> {
}
