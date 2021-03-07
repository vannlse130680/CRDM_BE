package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class SupplierEntity extends CrdmEntity<Integer> {

    private String name;

}
