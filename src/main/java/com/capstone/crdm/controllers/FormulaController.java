package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.FormulaEntity;
import com.capstone.crdm.repositories.FormulaRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.FormulaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RequestMapping(path = "/formula")
@RestController
public class FormulaController extends CrdmController<FormulaEntity, Integer, FormulaRepository> {

    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @Override
    protected CrdmService<FormulaEntity, Integer, FormulaRepository> getService() {
        return this.formulaService;
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> deployToProduction(@PathVariable Integer id, @RequestBody String status) {
        this.formulaService.updateFormulaStatus(id, status);
        return ResponseEntity.ok(id);
    }

    @PatchMapping(path = "/{id}/upgrade")
    public ResponseEntity<?> upgrade(@PathVariable Integer id, @RequestBody FormulaEntity formula) {
        formula.setId(id);
        this.formulaService.upgrade(formula);
        return ResponseEntity.ok(id);
    }

}
