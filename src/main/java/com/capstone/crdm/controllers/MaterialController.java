package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.MaterialEntity;
import com.capstone.crdm.repositories.MaterialRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.MaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
