package com.capstone.crdm.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "`User`") // phai co `` moi ko loi
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "DateOfBirth")
    private Timestamp dateOfBirth;

    @Basic
    @Column(name = "status")
    private int status;

    @Basic
    @Column(name = "roleId")
    private int roleId;

}
