package com.capstone.crdm.entities;

import com.capstone.crdm.exception.CrdmIllegalStateException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "formulas")
public class FormulaEntity extends CrdmEntity<Integer> implements Cloneable {

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

    @Override
    public FormulaEntity clone() throws CloneNotSupportedException {
        var clonedFormula = (FormulaEntity) super.clone();
        BeanUtils.copyProperties(this, clonedFormula);

        // clone children
        clonedFormula.phases = this.phases.stream().map(phase -> {
            try {
                var clonedPhase = phase.clone();
                clonedPhase.setFormula(clonedFormula);
                return clonedPhase;
            } catch (CloneNotSupportedException e) {
                throw new CrdmIllegalStateException("Cannot clone phases");
            }
        }).collect(Collectors.toList());

        clonedFormula.details = this.details.stream().map(detail -> {
            try {
                var clonedDetail = detail.clone();
                clonedDetail.setFormula(clonedFormula);
                return clonedDetail;
            } catch (CloneNotSupportedException e) {
                throw new CrdmIllegalStateException("Cannot clone formula details");
            }
        }).collect(Collectors.toList());

        return clonedFormula;
    }
}
