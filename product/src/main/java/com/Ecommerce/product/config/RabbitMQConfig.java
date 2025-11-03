package com.Ecommerce.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUCT_EXCHANGE = "product_exchange";

    // send from product to order to update order processing
    public static final String PRODUCT_VALIDATED_ORDER_QUEUE = "product_validated_product_queue";
    public static final String PRODUCT_VALIDATED_ROUTING_KEY = "product.validated";

    // send from product to wallet to update balance
    public static final String PRODUCT_VALIED_Wallet_QUEUE = "product_valied_wallet_queue";
    public static final String PRODUCT_VALIED_wallet_ROUTING_KEY = "product_wallet_valied.created";

    // recive from wallet to check stock
    public static final String WALLET_VALIDATED_PRODUCT_QUEUE = "wallet_validated_product_queue";


    public RabbitMQConfig() {
        // 👇 This will appear in your Docker logs if the config is loaded
        System.out.println("🚀 RabbitMQConfig loaded inside Docker container!");
    }
    @Bean
    public TopicExchange productExchange(){
        return  new TopicExchange(PRODUCT_EXCHANGE);
    }

    // this for order
    @Bean
    public Queue productValidateQueue(){
        return  new Queue(PRODUCT_VALIDATED_ORDER_QUEUE,true);
    }


    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(productValidateQueue())
                .to(productExchange())
                .with(PRODUCT_VALIDATED_ROUTING_KEY);
    }


    // this for wallet
    @Bean
    public Queue productValidateWalletQueue(){
        return  new Queue(PRODUCT_VALIED_Wallet_QUEUE,true);
    }


    @Bean
    public Binding walletBinding(){
        return BindingBuilder.bind(productValidateWalletQueue())
                .to(productExchange())
                .with(PRODUCT_VALIED_wallet_ROUTING_KEY);
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
