package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "material")
public class Material {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "supplier")
    private String supplier;

    @Basic
    @Column(name = "DDP")
    private String ddb;

    @Basic
    @Column(name = "Currently")
    private String currently;

}
