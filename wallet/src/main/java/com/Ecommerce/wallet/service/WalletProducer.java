package com.Ecommerce.wallet.service;


import com.Ecommerce.wallet.config.RabbitMQConfig;
import com.Ecommerce.wallet.model.dto.OrderValidationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletProducer {


    private final RabbitTemplate rabbitTemplate;
    public WalletProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void walletBalanceValidated(OrderValidationMessage message)
    {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.WALLET_EXCHANGE,
                RabbitMQConfig.WALLET_VALIDATED_ROUTING_KEY,
                message
        );
    }
}
