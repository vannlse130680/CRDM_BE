package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.Material;
import com.capstone.crdm.services.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/material")
@RestController
public class MaterialController {

    private MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Material material) {
        var createdProduct = this.materialService.create(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Material> update(@PathVariable Integer id, @RequestBody Material product) {
        product.setId(id);
        var updatedProduct = this.materialService.update(product);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Material> findById(@PathVariable Integer id) {
        var product = this.materialService.findById(id);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Material>> findAll() {
        var results = this.materialService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        this.materialService.delete(id);
    }

}
