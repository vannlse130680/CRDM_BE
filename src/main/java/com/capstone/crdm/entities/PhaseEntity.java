package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "phases")
public class PhaseEntity extends CrdmChildEntity<Integer> {

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


}
