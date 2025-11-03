package com.Ecommerce.order.service;


import com.Ecommerce.order.model.dto.OrderValidationMessage;
import com.Ecommerce.order.model.entity.Order;
import com.Ecommerce.order.model.entity.OrderDetailes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.Ecommerce.order.config.RabbitMQConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProduce {
    private final RabbitTemplate rabbitTemplate;
    public OrderProduce(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addOrder(OrderValidationMessage message)
    {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_CREATED_ROUTING_KEY,
                message
        );
    }
}
