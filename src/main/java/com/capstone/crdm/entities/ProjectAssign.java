package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "project_assignments")
public class ProjectAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int projectId;

    private int userId;

}
