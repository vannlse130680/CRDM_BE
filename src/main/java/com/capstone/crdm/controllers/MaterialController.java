package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.MaterialEntity;
import com.capstone.crdm.entities.MaterialTypeEntity;
import com.capstone.crdm.repositories.MaterialRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/material")
@RestController
public class MaterialController extends CrdmController<MaterialEntity, Integer, MaterialRepository> {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Override
    protected CrdmService<MaterialEntity, Integer, MaterialRepository> getService() {
        return this.materialService;
    }

    @GetMapping(path = "/types")
    public ResponseEntity<List<MaterialTypeEntity>> getAllTypes() {
        return ResponseEntity.ok(this.materialService.getAllMaterialTypes());
    }

}
