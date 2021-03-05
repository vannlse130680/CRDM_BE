package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.FormulaEntity;
import com.capstone.crdm.repositories.FormulaRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.FormulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
