package com.capstone.crdm.services;

import com.capstone.crdm.entities.MaterialEntity;
import com.capstone.crdm.entities.MaterialTypeEntity;
import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.repositories.MaterialRepository;
import com.capstone.crdm.repositories.MaterialTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MaterialService extends CrdmService<MaterialEntity, Integer, MaterialRepository> {

   private final MaterialRepository materialRepository;

   private final MaterialTypeRepository materialTypeRepository;

    public MaterialService(MaterialRepository productRepository, MaterialTypeRepository materialTypeRepository) {
        super(MaterialEntity.class);
        this.materialRepository = productRepository;
        this.materialTypeRepository = materialTypeRepository;
    }

    @Override
    protected MaterialRepository getRepository() {
        return this.materialRepository;
    }

    public List<MaterialTypeEntity> getAllMaterialTypes() {
        return new ArrayList<>((Collection<? extends MaterialTypeEntity>) this.materialTypeRepository.findAll());
    }

}
