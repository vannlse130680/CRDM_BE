package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.FormulaEntity;
import com.capstone.crdm.exception.CrdmOperationNotSupportedException;
import com.capstone.crdm.repositories.FormulaRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.FormulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.net.URI;
import java.util.List;

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

    @PostMapping(path = "/create")
    @Override
    public ResponseEntity<URI> create(FormulaEntity entity) {
        throw new CrdmOperationNotSupportedException("This operation is not supported in Formula");
    }


    @PutMapping(path = "/update")
    @Override
    public ResponseEntity<?> update(Integer integer, FormulaEntity entity) {
        return super.update(integer, entity);
    }

    @PostMapping
    public ResponseEntity<URI> createFormula(@RequestBody FormulaEntity entity) {
        return ResponseEntity.ok(URI.create(""));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateFormula(@PathVariable Integer id, @RequestBody FormulaEntity entity) {
        return ResponseEntity.ok(id);
    }

}
