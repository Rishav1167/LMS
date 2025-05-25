package com.lms.authms.domain.dto;

public record VerifyOtpRequest(
        String email,
        String otp
) {
}
