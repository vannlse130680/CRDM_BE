package com.capstone.crdm.entities;

import com.capstone.crdm.exception.CrdmIllegalStateException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "phases")
public class PhaseEntity extends CrdmChildEntity<Integer> implements Cloneable {

    private String name;

    private String description;

    @NotNull
    private int orderNumber;

    @NotNull
    @JsonManagedReference
    @OneToMany(mappedBy = "phase", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhaseDetailEntity> details;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private FormulaEntity formula;

    @Override
    public PhaseEntity clone() throws CloneNotSupportedException {
        var clonedPhase = (PhaseEntity) super.clone();

        clonedPhase.id = this.id;
        clonedPhase.name = this.name;
        clonedPhase.description = this.description;
        clonedPhase.details = this.details.stream().map(detail -> {
            try {
                var clonedDetail = detail.clone();
                clonedDetail.setPhase(clonedPhase);
                return clonedDetail;
            } catch (CloneNotSupportedException e) {
                throw new CrdmIllegalStateException("Cannot clone new phase detail");
            }
        }).collect(Collectors.toList());

        return clonedPhase;
    }
}
