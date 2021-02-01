package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.Client;
import com.capstone.crdm.entities.User;
import com.capstone.crdm.services.IUserService;
import com.capstone.crdm.utilities.CRDMMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("/user")
    public ResponseEntity getAllUser(){
        try {
            List<User> userList = userService.getAllUser();

            if (userList == null) {
                return new ResponseEntity("no client found", HttpStatus.OK);
            } else {
                return new ResponseEntity(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(new CRDMMessage(e.getMessage()),HttpStatus.CONFLICT);
        }
    }
    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity getAllUserByProjectId(@PathVariable("id") int id){
        try {
            List<User> userList = userService.findUserByProjectId(id);

            if (userList == null) {
                return new ResponseEntity("no client found", HttpStatus.OK);
            } else {
                return new ResponseEntity(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(new CRDMMessage(e.getMessage()),HttpStatus.CONFLICT);
        }
    }
}
