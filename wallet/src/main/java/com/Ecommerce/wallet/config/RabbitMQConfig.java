package com.Ecommerce.wallet.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfig {
    public static final String USER_QUEUE = "user_queue";
    public static final String ORDER_CREATED_WALLET_QUEUE = "order_created_wallet_queue";

    public static final String WALLET_VALIDATED_PRODUCT_QUEUE = "wallet_validated_product_queue";
    public static final String WALLET_EXCHANGE = "wallet_exchange";
    public static final String WALLET_VALIDATED_ROUTING_KEY = "wallet.validated";


    public RabbitMQConfig() {
        // 👇 This will appear in your Docker logs if the config is loaded
        System.out.println("🚀 RabbitMQConfig loaded inside Docker container!");
    }

    @Bean
    public Queue walletValidateQueue(){
        return  new Queue(WALLET_VALIDATED_PRODUCT_QUEUE,true);
    }

    @Bean
    public TopicExchange walletExchange(){
        return  new TopicExchange(WALLET_EXCHANGE);
    }

    @Bean
    public Binding userBinding(){
        return BindingBuilder.bind(walletValidateQueue())
                .to(walletExchange())
                .with(WALLET_VALIDATED_ROUTING_KEY);
    }



    // for serialization
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    // for serialization
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
