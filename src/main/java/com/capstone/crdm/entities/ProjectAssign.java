package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ProjectAssign")
public class ProjectAssign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

     @Basic
    @Column(name = "ProjectId")
    private int projectId;

    @Basic
    @Column(name = "UserId")
    private int userId;
}
