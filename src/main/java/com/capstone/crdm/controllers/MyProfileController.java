package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.security.utils.AuthorizationUtils;
import com.capstone.crdm.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RequestMapping(path = "/my-profile")
@RestController
public class MyProfileController {

    private final UserService userService;

    public MyProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserEntity> getProfile() {
        return ResponseEntity.ok(this.userService.findById(AuthorizationUtils.getCurrentUserId()));
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
