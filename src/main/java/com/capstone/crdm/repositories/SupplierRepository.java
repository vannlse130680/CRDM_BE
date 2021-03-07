package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.SupplierEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrdmRepository<SupplierEntity, Integer> {
}
