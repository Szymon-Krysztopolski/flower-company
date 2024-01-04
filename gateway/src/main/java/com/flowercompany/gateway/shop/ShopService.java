package com.flowercompany.gateway.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ShopService {
    private static WebClient shopApiClient;

    @Autowired
    public ShopService(WebClient shopApiClient) {
        ShopService.shopApiClient = shopApiClient;
    }

    public String order(Object object) {
        return "I got bouquet";
    }

    public List<String> checkOrders() {
        return List.of("id1", "id2");
    }
}
