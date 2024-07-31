package com.api.rating_product.infra.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    @Bean
    public Queue createQueueReview(){

        return QueueBuilder.durable("review.ms").build();

    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){

        return rabbitAdmin(connectionFactory);

    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent>initAdmin(RabbitAdmin rabbitAdmin){

        return event -> rabbitAdmin.initialize();

    }

    @Bean
    public FanoutExchange createExchangeReview(){

        return ExchangeBuilder.fanoutExchange("review.ex").build();

    }

    @Bean
    public Binding createBinding(){

        return BindingBuilder.bind(createQueueReview()).to(createExchangeReview());

    }
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){

        return new Jackson2JsonMessageConverter();

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;

    }



}
