package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "FormulaVersion")
public class
Formula {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "VersionCodeBaseOn")
    private String versionCodeBaseOn;


    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;

    @Basic
    @Column(name = "LastEdittedDate")
    private Timestamp LastEdittedDate;
    @Basic
    @Column(name = "Status")
    private int status;

    @Basic
    @Column(name = "OutsourceTestFile")
    private String outsourceTestFile;

    @Basic
    @Column(name = "ProjectId")

    private int projectId;

}
