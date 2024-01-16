package com.flowercompany.factory.broker;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue flowerQueue() {
        return new Queue("flowerQueue", false);
    }

    @Bean
    public SimpleMessageConverter converter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of(
                "com.flowercompany.*",
                "java.util.*",
                "java.lang.Enum"
        ));
        return converter;
    }
}
