package com.Ecommerce.product.service;

import com.Ecommerce.product.config.RabbitMQConfig;
import com.Ecommerce.product.model.dto.OrderValidationMessage;
import com.Ecommerce.product.model.entity.Product;
import com.Ecommerce.product.repository.ProductServiceInterface;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductConsumer {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @Autowired
    ProductProducer productProducer;

    @RabbitListener(queues = RabbitMQConfig.WALLET_VALIDATED_PRODUCT_QUEUE)
    public  void asdWallet(OrderValidationMessage message){
        System.out.println("sss");
        Boolean product_valied = true;
        for (int i = 0; i < message.getProduct_ids().size(); i++) {
            Long productId = message.getProduct_ids().get(i);
            Integer count = message.getCounts().get(i);
            Product product = productServiceInterface.findById(productId).get();
            if (count >= product.getStock()) {
                System.out.println("fail");
                message.setProduct_valied(false);
                product_valied = false;
                productProducer.walletBalanceValidated(message);
            }
        }
        if (product_valied.booleanValue()==true){
            for (int i = 0; i < message.getProduct_ids().size(); i++) {
                System.out.println("success");
                Long productId = message.getProduct_ids().get(i);
                Integer count = message.getCounts().get(i);
                Product productentity = productServiceInterface.findById(productId).get();
                productentity.setStock(productentity.getStock() - count);
                productentity.setReserved(productentity.getReserved() + count);
                productServiceInterface.save(productentity);
            }
            message.setProduct_valied(true);
            productProducer.walletBalanceValidated(message);
        }
        // Simulate delay (optional)
        try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
