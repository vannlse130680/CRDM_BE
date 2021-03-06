package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "formulas")
public class FormulaEntity extends CrdmEntity<Integer> {

    private Instant createdAt;

    private int createdBy;

    private Instant updatedAt;

    private int updatedBy;

    private int upgradedFrom;

    private String outsourceTestFileLink;

    private int projectId;

    private int versionId;

    private String changeNote;

    @JsonManagedReference
    @OneToMany(mappedBy = "formula", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhaseEntity> details;
}
