package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RequestMapping(path = "/profile")
@RestController
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody UserEntity updatedProfile) {
        this.userService.updateProfile(updatedProfile);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<?> updatePassword(@RequestBody String newPassword) {
        this.userService.updatePassword(newPassword);
        return ResponseEntity.noContent().build();
    }

}
