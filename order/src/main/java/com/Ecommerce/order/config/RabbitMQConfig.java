package com.Ecommerce.order.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.AbstractJackson2MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


@Configuration
public class RabbitMQConfig {

    // 🧾 Exchange
    public static final String ORDER_EXCHANGE = "orders.exchange";
    //  send from order to wallet to chcek wallet
    public static final String ORDER_CREATED_WALLET_QUEUE = "order_created_wallet_queue";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";
    //  recive from product to order to if no stock
    public static final String PRODUCT_VALIDATED_ORDER_QUEUE = "product_validated_product_queue";
    // recive from wallet to order to cancel order if balance didn't enough
    public static final String WALLET_VALIDATED_ORDER_QUEUE = "wallet_validated_order_queue";


    public RabbitMQConfig() {
        // 👇 This will appear in your Docker logs if the config is loaded
        System.out.println("🚀 RabbitMQConfig loaded inside Docker container!");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter() {
        };
    }

    // Create Exchange
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE, true, false);
    }




    // Create Wallet Queue
    @Bean
    public Queue orderValiedWalletQueue() {
        return new Queue(ORDER_CREATED_WALLET_QUEUE, true);
    }

    // Bind Wallet Queue to Exchange
    @Bean
    public Binding orderCreatedWalletBinding(Queue orderCreatedWalletQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedWalletQueue)
                .to(orderExchange)
                .with(ORDER_CREATED_ROUTING_KEY);
    }



    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
