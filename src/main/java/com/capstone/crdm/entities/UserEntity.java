package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends CrdmEntity<Integer> {

    private String username;

    private String password;

    private String name;

    private String phone;

    private Instant dateOfBirth;

    private String role;

//    @OneToMany
//    @JoinColumn(name ="userId",insertable = false, updatable = false)
//    private List<ProjectAssign> projectAssign;

    @Transient
    private int roleId;

}
