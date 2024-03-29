package com.flowercompany.shop.broker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue flowerQueue() {
        return new Queue("flowerQueue", false);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("errorQueue", false);
    }
}
