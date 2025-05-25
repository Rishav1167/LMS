package com.lms.notificationms.domain.dto;

import com.lms.notificationms.domain.enums.StatusType;

import java.util.UUID;

public record NotificationMessage(
        String exchange,
        String routingKey,
        UUID statusId,
        UUID candidateId,
        StatusType statusType
) {
}
