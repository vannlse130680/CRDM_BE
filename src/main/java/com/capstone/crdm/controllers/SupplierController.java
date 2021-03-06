package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.SupplierEntity;
import com.capstone.crdm.repositories.SupplierRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.SupplierService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/suppliers")
@RestController
public class SupplierController extends CrdmController<SupplierEntity, Integer, SupplierRepository> {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    protected CrdmService<SupplierEntity, Integer, SupplierRepository> getService() {
        return this.supplierService;
    }

}
