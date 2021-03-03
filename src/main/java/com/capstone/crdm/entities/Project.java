package com.capstone.crdm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String product;

    private String requirement;
    
    private Instant createdAt;

    private Integer createdBy;
    
    private Instant deadline;

    private String status;

    private int clientId;
//
//    @ManyToOne
//    @JoinColumn(name ="clientId",insertable = false, updatable = false)
//    private Client client;
//
//    @OneToMany
//    @JoinColumn(name ="projectId",insertable = false, updatable = false)
//    private List<ProjectAssign> projectAssign;

}
