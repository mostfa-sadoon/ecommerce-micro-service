package com.Ecommerce.order.service;


import com.Ecommerce.order.config.RabbitMQConfig;
import com.Ecommerce.order.model.dto.OrderValidationMessage;
import com.Ecommerce.order.model.entity.Order;
import com.Ecommerce.order.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletConsumer {

    @Autowired
    OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.WALLET_VALIDATED_ORDER_QUEUE)
    public void checkBalance(OrderValidationMessage message){
        if(message.getWallet_valied().booleanValue() == false){
            System.out.println("order is canceled");
            Order orderentity = orderRepository.findById(message.getOrder_id()).get();
            orderentity.setStatus(Order.Status.valueOf("CANCELD"));
            orderRepository.save(orderentity);
        }
    }


}
