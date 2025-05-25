package com.examservice.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExamDTO(
        UUID id,
        String examName,
        LocalDateTime examDate,
        UUID studentId,
//        Long notificationId,
        String status
){
}
