package com.lms.userservice.domain.dto;


import com.lms.userservice.domain.enums.Role;

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
