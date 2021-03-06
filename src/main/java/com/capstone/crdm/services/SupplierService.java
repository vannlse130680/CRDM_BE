package com.capstone.crdm.services;

import com.capstone.crdm.entities.SupplierEntity;
import com.capstone.crdm.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends CrdmService<SupplierEntity, Integer, SupplierRepository> {

    private final SupplierRepository supplierRepository;

    protected SupplierService(SupplierRepository supplierRepository) {
        super(SupplierEntity.class);
        this.supplierRepository = supplierRepository;
    }

    @Override
    protected SupplierRepository getRepository() {
        return this.supplierRepository;
    }
}
