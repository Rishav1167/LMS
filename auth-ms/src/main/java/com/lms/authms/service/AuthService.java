package com.lms.authms.service;

import com.lms.authms.config.RabbitConfig;
import com.lms.authms.domain.dto.*;
import com.lms.authms.domain.entity.User;
import com.lms.authms.domain.mapper.UserMapper;
import com.lms.authms.exception.LoginException;
import com.lms.authms.exception.RegisterException;
import com.lms.authms.repository.UserDetailsRepository;
import com.lms.authms.service.producer.OtpProducerService;
import com.lms.authms.service.producer.UserProducerService;
import com.lms.authms.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsRepository userDetailsRepository;
    private final UserMapper userMapper;
    private final OtpProducerService otpProducerService;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final UserProducerService userProducerService;

    public AuthService(final JwtUtil jwtUtil,
                       final AuthenticationManager authenticationManager,
                       final UserDetailsRepository userDetailsRepository,
                       final UserMapper userMapper,
                       final OtpProducerService otpProducerService,
                       final PasswordEncoder passwordEncoder,
                       final OtpService otpService,
                       final UserProducerService userProducerService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsRepository = userDetailsRepository;
        this.userMapper = userMapper;
        this.otpProducerService = otpProducerService;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.userProducerService = userProducerService;
    }

    public AuthResponse login(AuthRequest authRequest) {
        try{
            User user = authenticate(authRequest.email(), authRequest.password());
            String otp = otpService.generateOtp(authRequest.email());

            otpProducerService.sendMessageToNotificationMS(new OtpMessage(
                    RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.OTP_SENT_ROUTING_KEY,
                    authRequest.email(),
                    otp
            ));
            return new AuthResponse("OTP sent to " + authRequest.email());
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
    }

    public RegisterResponse register(RegisterRequest registerRequest){
        try{
            userDetailsRepository.save(userMapper.toUser(registerRequest));

            User user = authenticate(registerRequest.email(), registerRequest.password());

            String otp = otpService.generateOtp(registerRequest.email());

            otpProducerService.sendMessageToNotificationMS(new OtpMessage(
                    RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.OTP_SENT_ROUTING_KEY,
                    registerRequest.email(),
                    otp
            ));

            userProducerService.publishUserCreatedEvent(user);
            return new RegisterResponse("OTP sent to " + registerRequest.email());
        } catch (Exception e) {
            throw new RegisterException(e.getMessage());
        }
    }

    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest){
        String email = verifyOtpRequest.email();
        String otp = verifyOtpRequest.otp();

        if (otpService.validateOtp(email, otp)){
            User user = userDetailsRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));

            otpService.deleteOtp(email);
            return new VerifyOtpResponse(true, jwtUtil.generateToken(user));
        }
        return new VerifyOtpResponse(false, null);
    }

    public String forgetPassword(ForgotPasswordRequest forgotPasswordRequest){
        String email = forgotPasswordRequest.email();

        User user = userDetailsRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = otpService.generateOtp(forgotPasswordRequest.email());

        otpProducerService.sendMessageToNotificationMS(new OtpMessage(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.OTP_SENT_ROUTING_KEY,
                email,
                otp
        ));

        return "OTP sent to " + email;
    }

    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest){
        String email = resetPasswordRequest.email();
        String otp = resetPasswordRequest.otp();
        String newPassword = passwordEncoder.encode(resetPasswordRequest.newPassword());

        if (otpService.validateOtp(email, otp)){
            User user = userDetailsRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));
            otpService.deleteOtp(email);
            user.setPassword(newPassword);
            userDetailsRepository.save(user);
            return true;
        }
        return false;
    }


    private User authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return userDetailsRepository.findByEmail(username)
                .orElseThrow(() -> new LoginException("User not found"));
    }
}