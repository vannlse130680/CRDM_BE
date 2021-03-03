package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "formulas")
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Instant createdAt;

    private int createdBy;

    private Instant updatedAt;

    private int updatedBy;

    private int upgradedFrom;

    private String outsourceTestFileLink;

    private int projectId;

    private int versionId;

    private String changeNote;

    private String status;

}
