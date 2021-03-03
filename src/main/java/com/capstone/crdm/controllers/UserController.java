package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.repositories.UserRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/users")
@RestController
public class UserController extends CrdmController<UserEntity, Integer, UserRepository> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected CrdmService<UserEntity, Integer, UserRepository> getService() {
        return this.userService;
    }

}
