package com.flowercompany.gateway.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.flowercompany.gateway.factory.ws");
        return marshaller;
    }

    @Bean
    public StatusClient statusClient(Jaxb2Marshaller marshaller) {
        StatusClient client = new StatusClient();
        client.setDefaultUri("http://localhost:8083/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
