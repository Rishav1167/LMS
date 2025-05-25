package com.studentms.domain.dto;

import java.util.UUID;

public record StudentDto(
        UUID id,
        String name,
        String email,
        UUID userId,
        UUID batchId,
        UUID courseId
) {
}
