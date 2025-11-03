package com.Ecommerce.order.service;

import com.Ecommerce.order.config.RabbitMQConfig;
import com.Ecommerce.order.model.dto.OrderValidationMessage;
import com.Ecommerce.order.model.entity.Order;
import com.Ecommerce.order.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductConsumer {
    @Autowired
    OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_VALIDATED_ORDER_QUEUE)
    public void checkBalance(OrderValidationMessage message){
       if(message.getProduct_valied().booleanValue() == true){
           Order orderentity = orderRepository.findById(message.getOrder_id()).get();
           orderentity.setStatus(Order.Status.valueOf("PROCCESS"));
           orderRepository.save(orderentity);
       }
    }
}
