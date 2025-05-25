package com.lms.userservice.controller;

import com.lms.userservice.domain.entity.UserProfile;
import com.lms.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserProfile userProfile) {
        UserProfile saved = service.createProfile(userProfile);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/auth-id/{authUserId}")
    public ResponseEntity<?> getByAuthId(@PathVariable UUID authUserId) {
        return service.getProfileByAuthId(authUserId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public List<UserProfile> getAll() {
        return service.getAllProfiles();
    }
}