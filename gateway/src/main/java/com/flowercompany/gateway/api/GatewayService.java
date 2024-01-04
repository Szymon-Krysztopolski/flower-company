package com.flowercompany.gateway.api;

import com.flowercompany.gateway.factory.FactoryService;
import com.flowercompany.gateway.shop.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayService {
    private final ShopService shopService;
    private final FactoryService factoryService;

    public GatewayService(ShopService shopService, FactoryService factoryService) {
        this.shopService = shopService;
        this.factoryService = factoryService;
    }

    public String order(Object object) {
        return shopService.order(object);
    }

    public List<String> checkOrders() {
        return shopService.checkOrders();
    }

    public String checkOrderStatus(String id) {
        return factoryService.checkOrderStatus(id);
    }
}
