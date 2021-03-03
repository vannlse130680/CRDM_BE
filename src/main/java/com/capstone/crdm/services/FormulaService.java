package com.capstone.crdm.services;

import com.capstone.crdm.entities.FormulaEntity;
import com.capstone.crdm.repositories.FormulaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FormulaService extends CrdmService<FormulaEntity, Integer, FormulaRepository> {

    private final FormulaRepository formulaRepository;

    public FormulaService(FormulaRepository productRepository) {
        super(FormulaEntity.class);
        this.formulaRepository = productRepository;
    }

    @Override
    protected FormulaRepository getRepository() {
        return this.formulaRepository;
    }

}
