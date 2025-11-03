package com.Ecommerce.product.service;


import com.Ecommerce.product.config.RabbitMQConfig;
import com.Ecommerce.product.model.dto.OrderValidationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    private final RabbitTemplate rabbitTemplate;
    public ProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void walletBalanceValidated(OrderValidationMessage message)
    {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PRODUCT_EXCHANGE,
                RabbitMQConfig.PRODUCT_VALIDATED_ROUTING_KEY,
                message
        );
    }

}
