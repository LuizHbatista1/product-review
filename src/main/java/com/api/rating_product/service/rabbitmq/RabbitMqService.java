package com.api.rating_product.service.rabbitmq;

import com.api.rating_product.domain.review.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private RabbitTemplate rabbitTemplate;

    public void NotificationQueue(String exchange , Review review){

        rabbitTemplate.convertAndSend(exchange , "",review);

    }



}
