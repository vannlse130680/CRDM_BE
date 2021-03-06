package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "phases")
public class PhaseEntity extends CrdmChildEntity<Integer> {

    private String name;

    private String description;

    private int orderNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "phase", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhaseDetailEntity> details;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private FormulaEntity formula;


}
