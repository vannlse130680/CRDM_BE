package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Formula {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Basic
    @Column(name = "code")
    private String name;


    @Basic
    @Column(name = "lastEditDate")
    private String lastEditDate;


    @Basic
    @Column(name = "projectId")
    private String projectId;


    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "createBy")
    private String createBy;

    @Basic
    @Column(name = "versionId")
    private String versionId;

}
