package com.contentms.domain.dto;

import java.util.UUID;

public record ContentDTO(
        UUID id,
        String title,
        String description,
        String mediaUrl,
        UUID courseId,
        UUID instructorId,
        UUID studentId
) {}
