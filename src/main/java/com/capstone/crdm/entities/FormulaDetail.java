package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "formula_details")
public class FormulaDetail extends CrdmChildEntity<Integer> implements Cloneable {

    @NotNull
    private int materialId;

    @NotNull
    private Double percentage;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private FormulaEntity formula;

    @Override
    protected FormulaDetail clone() throws CloneNotSupportedException {
        var clonedFormulaDetail = (FormulaDetail) super.clone();
        clonedFormulaDetail.id = this.id;
        clonedFormulaDetail.materialId = this.materialId;
        clonedFormulaDetail.percentage = this.percentage;

        return clonedFormulaDetail;
    }

}
