package com.api.rating_product.infra.rabbitmq;

import com.api.rating_product.DTOS.review.ReviewDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    @RabbitListener
    public void listen(Message<ReviewDTO> message){


    }

}
