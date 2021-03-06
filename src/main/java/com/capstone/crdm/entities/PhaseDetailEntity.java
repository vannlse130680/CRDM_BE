package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "phase_details")
public class PhaseDetailEntity extends CrdmChildEntity<Integer> {

    private int materialId;

    private Double percentage;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private PhaseEntity phase;

}