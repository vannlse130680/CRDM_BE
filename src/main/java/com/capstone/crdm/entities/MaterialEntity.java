package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "material")
public class MaterialEntity extends CrdmEntity<Integer> {

    private String name;

    private int supplier_id;

    private Double ddp;

    private String currency;

}
