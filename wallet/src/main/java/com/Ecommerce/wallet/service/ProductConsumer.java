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
public class ProductConsumer {

    @Autowired
    WalletRepository walletRepository;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_VALIED_Wallet_QUEUE)
    public void changeBalance(OrderValidationMessage message){
        System.out.println("Received message: " + message);
        Optional<Wallet> wallet = walletRepository.findByUserId(message.getUser_id());
        if(wallet.isPresent()){
            Wallet walletEntity = wallet.get();
            walletEntity.setBalance(walletEntity.getBalance()-message.getPrice());
            walletRepository.save(walletEntity);
            System.out.println("wallet updated successfully");
        }
    }



}
