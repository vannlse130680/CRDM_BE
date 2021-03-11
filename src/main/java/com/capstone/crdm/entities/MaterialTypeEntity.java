package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "material_types")
public class MaterialTypeEntity extends CrdmChildEntity<Integer> {
    private String type;
}
