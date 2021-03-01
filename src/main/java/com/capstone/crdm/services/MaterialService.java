package com.capstone.crdm.services;

import com.capstone.crdm.entities.Material;
import com.capstone.crdm.repositories.IMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MaterialService {

   private final IMaterialRepository materialRepository;

    public MaterialService(IMaterialRepository productRepository) {
        this.materialRepository = productRepository;
    }

    public Material create(Material product) {
        return this.materialRepository.save(product);
    }

    public Material update(Material product) {
        return this.materialRepository.save(product);
    }

    public Material findById(Integer id) {
        return this.materialRepository.findById(id).orElse(null);
    }

    public List<Material> findAll() {
        return new ArrayList<>((Collection<? extends Material>) this.materialRepository.findAll());
    }

    public void delete(Integer id) {
        this.materialRepository.deleteById(id);
    }
}
