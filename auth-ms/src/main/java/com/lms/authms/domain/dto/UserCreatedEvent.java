package com.lms.authms.domain.dto;

import com.lms.authms.domain.enums.Role;

import java.io.Serializable;
import java.util.UUID;

public record UserCreatedEvent(
        UUID authUserId,
        String name,
        String email,
        Role role,
        String routingKey
) implements Serializable {
}
