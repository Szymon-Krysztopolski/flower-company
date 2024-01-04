package com.flowercompany.gateway.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {
    private final StatusClient statusClient;

    @Autowired
    public FactoryService(StatusClient statusClient) {
        this.statusClient = statusClient;
    }

    public String checkOrderStatus(String id) {
        return statusClient.getStatus(id).toString();
    }
}
