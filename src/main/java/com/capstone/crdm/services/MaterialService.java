package com.capstone.crdm.services;

import com.capstone.crdm.entities.MaterialEntity;
import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.repositories.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MaterialService extends CrdmService<MaterialEntity, Integer, MaterialRepository> {

   private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository productRepository) {
        super(MaterialEntity.class);
        this.materialRepository = productRepository;
    }

    @Override
    protected MaterialRepository getRepository() {
        return this.materialRepository;
    }

}
