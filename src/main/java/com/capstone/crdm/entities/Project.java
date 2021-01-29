package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "product")
    private String product;

    @Basic
    @Column(name = "requirement")
    private String requirement;

    @Basic
    @Column(name = "CreatedDate")
    private Timestamp createdDate;

    @Basic
    @Column(name = "Deadline")
    private Timestamp deadline;
    @Basic
    @Column(name = "Status")
    private int status;

    @Basic
    @Column(name = "clientId",  updatable = false)

    private int clientId;

    @ManyToOne
    @JoinColumn(name ="clientId",insertable = false, updatable = false)

    private Client client;



}
