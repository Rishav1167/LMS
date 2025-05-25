package com.lms.userservice.service;

import com.lms.userservice.domain.entity.UserProfile;
import com.lms.userservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository repository;

    public UserProfile createProfile(UserProfile profile) {
        return repository.save(profile);
    }

    public Optional<UserProfile> getProfileByAuthId(UUID authUserId) {
        return repository.findByAuthUserId(authUserId);
    }

    public List<UserProfile> getAllProfiles() {
        return repository.findAll();
    }
}
