package com.lms.userservice.domain.entity;

import com.lms.userservice.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserProfile {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private UUID authUserId;
    private String name;
    private String email;
    private Role role;
}