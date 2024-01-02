package com.flowercompany.gateway.api;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayService {
    public String order(Object object) {
        return "I got bouquet";
    }

    public List<String> checkOrders() {
        return List.of("id1", "id2");
    }

    public String checkOrderStatus(String id) {
        return "example product with id: " + id;
    }
}
