package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private int projectId;

    private int versionId;

    private String changeNote;

    @NotNull
    @JsonManagedReference
    @OneToMany(mappedBy = "formula", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhaseEntity> phases;

    @NotNull
    @JsonManagedReference
    @OneToMany(mappedBy = "formula", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormulaDetail> details;
}
