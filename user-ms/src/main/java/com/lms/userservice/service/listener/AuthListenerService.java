package com.lms.userservice.service.listener;

import com.lms.userservice.config.RabbitConfig;
import com.lms.userservice.domain.dto.UserCreatedEvent;
import com.lms.userservice.domain.entity.UserProfile;
import com.lms.userservice.service.UserProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthListenerService {
    @Autowired
    private UserProfileService userProfileService;

    @RabbitListener(queues = RabbitConfig.LMS_AUTH_QUEUE)
    public void handleQueue(UserCreatedEvent event) {
        if (event.routingKey().equals("auth.created")) {
            userProfileService.createProfile(
                    UserProfile.builder()
                            .authUserId(event.authUserId())
                            .email(event.email())
                            .name(event.name())
                            .role(event.role())
                            .build()
            );
        }
    }

}
