package com.Ecommerce.wallet.service;


import com.Ecommerce.wallet.config.RabbitMQConfig;
import com.Ecommerce.wallet.model.dto.OrderValidationMessage;
import com.Ecommerce.wallet.model.entity.Wallet;
import com.Ecommerce.wallet.repository.WalletRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderConsumer {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    WalletProducer walletProducer;

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_WALLET_QUEUE)
    public void checkBalance(OrderValidationMessage message){
        System.out.println("Received message: " + message);
        Optional<Wallet> wallet = walletRepository.findByUserId(message.getUser_id());
        if(wallet.isPresent()){

            if (wallet.get().getBalance() > message.getPrice()) {
                System.out.println("✅ Balance is sufficient");
                walletProducer.walletBalanceValidated(message);
            } else {
                System.out.println("❌ Insufficient balance");
            }

        }
    }

}
