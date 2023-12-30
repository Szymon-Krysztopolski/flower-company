package com.flowercompany.gateway.factory;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    private StatusClient statusClient;

    @Autowired
    public FactoryService(StatusClient statusClient) {
        this.statusClient = statusClient;
    }

    @PostConstruct
    public void test() {
        statusClient.getStatus("1");
    }
}
