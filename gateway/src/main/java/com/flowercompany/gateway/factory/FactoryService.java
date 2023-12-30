package com.flowercompany.gateway.factory;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {
    private final StatusClient statusClient;

    @Autowired
    public FactoryService(StatusClient statusClient) {
        this.statusClient = statusClient;
    }

    @PostConstruct
    public void test() {
        System.out.println(statusClient);
        System.out.println(statusClient.getStatus("1").getStatus().getCode());
        System.out.println(statusClient.getStatus("1").getStatus().getPrice());
    }
}
