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

    public static final String WALLET_VALIDATED_PRODUCT_QUEUE = "wallet_validated_product_queue";


    public static final String PRODUCT_VALIDATED_ORDER_QUEUE = "product_validated_product_queue";
    public static final String PRODUCT_EXCHANGE = "product_exchange";
    public static final String PRODUCT_VALIDATED_ROUTING_KEY = "product.validated";



    public RabbitMQConfig() {
        // 👇 This will appear in your Docker logs if the config is loaded
        System.out.println("🚀 RabbitMQConfig loaded inside Docker container!");
    }

    @Bean
    public Queue productValidateQueue(){
        return  new Queue(PRODUCT_VALIDATED_ORDER_QUEUE,true);
    }

    @Bean
    public TopicExchange productExchange(){
        return  new TopicExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    public Binding userBinding(){
        return BindingBuilder.bind(productValidateQueue())
                .to(productExchange())
                .with(PRODUCT_VALIDATED_ROUTING_KEY);
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
