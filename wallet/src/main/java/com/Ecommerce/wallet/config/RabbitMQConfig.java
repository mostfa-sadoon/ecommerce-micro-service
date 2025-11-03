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
    // wallet echange
    public static final String WALLET_EXCHANGE = "wallet_exchange";

    // send from wallet to product to check stock
    public static final String WALLET_VALIDATED_PRODUCT_QUEUE = "wallet_validated_product_queue";
    public static final String WALLET_VALIDATED_ROUTING_KEY = "wallet.validated";
    // send from wallet to order if not enough balance
    public static final String WALLET_VALIDATED_ORDER_QUEUE = "wallet_validated_order_queue";
    public static final String WALLET_VALIDATED_ORDER_ROUTING_KEY = "wallet.validated.order";


    // reciveing from order to check balance
    public static final String ORDER_CREATED_WALLET_QUEUE = "order_created_wallet_queue";
    // recive from product to update balannce
    public static final String PRODUCT_VALIED_Wallet_QUEUE = "product_valied_wallet_queue";
    // recive from user to check balance
    public static final String USER_QUEUE = "user_queue";

    public RabbitMQConfig() {
        // 👇 This will appear in your Docker logs if the config is loaded
        System.out.println("🚀 RabbitMQConfig loaded inside Docker container!");
    }

    @Bean
    public TopicExchange walletExchange(){
        return  new TopicExchange(WALLET_EXCHANGE);
    }

    @Bean
    public Queue walletValidateQueue(){
        return  new Queue(WALLET_VALIDATED_PRODUCT_QUEUE,true);
    }

    @Bean
    public Queue walletValidateOrderQueue(){
        return  new Queue(WALLET_VALIDATED_ORDER_QUEUE,true);
    }



    @Bean
    public Binding walletProductBinding(){
        return BindingBuilder.bind(walletValidateQueue())
                .to(walletExchange())
                .with(WALLET_VALIDATED_ROUTING_KEY);
    }

    @Bean
    public Binding walletOrderBinding(){
        return BindingBuilder.bind(walletValidateOrderQueue())
                .to(walletExchange())
                .with(WALLET_VALIDATED_ORDER_ROUTING_KEY);
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
