package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class ProjectEntity extends CrdmEntity<Integer> {

    private String product;

    private String requirement;
    
    private Instant createdAt;

    private Integer createdBy;
    
    private Instant deadline;

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
