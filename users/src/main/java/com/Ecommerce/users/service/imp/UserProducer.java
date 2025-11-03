package com.Ecommerce.users.service.imp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.Ecommerce.users.config.RabbitMQConfig;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;
    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addUser(Long user_id)
    {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.USER_EXCHANGE,
                RabbitMQConfig.USER_ROUTING_KEY,
                user_id
        );
    }
}
