package com.capstone.crdm.services;

import com.capstone.crdm.entities.Formula;
import com.capstone.crdm.repositories.IFormulaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FormulaService {
    private IFormulaRepository formulaRepository;
    public FormulaService(IFormulaRepository productRepository) {
        this.formulaRepository = productRepository;
    }

    public Formula create(Formula product) {
        return this.formulaRepository.save(product);
    }

    public Formula update(Formula product) {
        return this.formulaRepository.save(product);
    }

    public Formula findById(Integer id) {
        return this.formulaRepository.findById(id).orElse(null);
    }

    public List<Formula> findAll() {
        return new ArrayList<>((Collection<? extends Formula>) this.formulaRepository.findAll());
    }

    public void delete(Integer id) {
        this.formulaRepository.deleteById(id);
    }

    public List<Formula> findByProjectId(int projectId) {
        return new ArrayList<>((Collection<? extends Formula>) this.formulaRepository.findByProjectId(projectId));
    }
}
