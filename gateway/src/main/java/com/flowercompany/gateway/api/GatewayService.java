package com.flowercompany.gateway.api;

import com.flowercompany.gateway.dto.Bouquet;
import com.flowercompany.gateway.dto.Order;
import com.flowercompany.gateway.factory.FactoryService;
import com.flowercompany.gateway.factory.ws.Status;
import com.flowercompany.gateway.shop.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GatewayService {
    private final ShopService shopService;
    private final FactoryService factoryService;

    public GatewayService(ShopService shopService, FactoryService factoryService) {
        this.shopService = shopService;
        this.factoryService = factoryService;
    }

    public String order(Bouquet bouquet) {
        return shopService.order(bouquet);
    }

    public List<Order> checkOrders() {
        return shopService.checkOrders().stream().map(id -> new Order(id, "OK")).collect(Collectors.toList());
    }

    public Status checkOrderStatus(String id) {
        return factoryService.checkOrderStatus(id);
    }
}
