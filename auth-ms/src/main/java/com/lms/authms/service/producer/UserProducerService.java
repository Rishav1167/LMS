package com.lms.authms.service.producer;

import com.lms.authms.config.RabbitConfig;
import com.lms.authms.domain.dto.UserCreatedEvent;
import com.lms.authms.domain.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishUserCreatedEvent(User user) {
        UserCreatedEvent event = new UserCreatedEvent(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                RabbitConfig.USER_CREATED_ROUTING_KEY
        );
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.USER_CREATED_ROUTING_KEY, event);
    }

}