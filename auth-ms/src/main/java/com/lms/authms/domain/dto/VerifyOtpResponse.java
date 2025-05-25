package com.lms.authms.domain.dto;

public record VerifyOtpResponse(
        boolean success,
        String token
) {
}
