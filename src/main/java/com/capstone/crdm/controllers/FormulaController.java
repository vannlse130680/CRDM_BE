package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.Formula;
import com.capstone.crdm.services.FormulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/formula")
@RestController
public class FormulaController {

    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Formula formula) {
        var createdProduct = this.formulaService.create(formula);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Formula> update(@PathVariable Integer id, @RequestBody Formula formula) {
        formula.setId(id);
        var updatedProduct = this.formulaService.update(formula);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Formula> findById(@PathVariable Integer id) {
        var product = this.formulaService.findById(id);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Formula>> findAll() {
        var results = this.formulaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        this.formulaService.delete(id);
    }
}
