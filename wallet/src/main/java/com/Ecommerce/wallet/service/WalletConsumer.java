package com.Ecommerce.wallet.service;

import com.Ecommerce.wallet.config.RabbitMQConfig;
import com.Ecommerce.wallet.model.entity.Wallet;
import com.Ecommerce.wallet.repository.WalletRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletConsumer {

  @Autowired
  WalletRepository walletRepository;

    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public  void addWallet(Long user_id){
        System.out.println(" user id is "+ user_id);
        Wallet wallet = Wallet.builder()
                .userId(user_id)
                .balance(0)
                .build();

        walletRepository.save(wallet);
        // Simulate delay (optional)
        try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
