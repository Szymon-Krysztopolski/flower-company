package com.flowercompany.shop.api;

import com.flowercompany.shop.dto.BouquetItem;
import com.flowercompany.shop.domain.OrderStatus;
import com.flowercompany.shop.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ShopService {
    private final Map<UUID, OrderStatus> orders = new HashMap<>();

    public String order(List<BouquetItem> bouquet) throws Exception {
        log.info("[Shop] Processing order..");

        Random random = new Random();
        int secondsOfShoppingProcess = 3;
        int successProbability = 90;

        Thread.sleep(random.nextInt(1000 * secondsOfShoppingProcess));

        if (random.nextInt(100) < successProbability) {
            UUID uuid = UUID.randomUUID();
            orders.put(uuid, OrderStatus.PROCESSING);

            return "Order was placed with id " + uuid;
        } else {
            log.error("[Shop] Error with query!");
            throw new OrderException();
        }
    }

    public List<UUID> getOrders() {
        return orders.keySet().stream().toList();
    }
}
