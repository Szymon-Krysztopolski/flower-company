package com.flowercompany.shop.api;

import com.flowercompany.shop.dto.Bouquet;
import com.flowercompany.shop.domain.OrderStatus;
import com.flowercompany.shop.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ShopService {
    private final RabbitTemplate rabbitTemplate;
    private final Map<UUID, OrderStatus> orders = new HashMap<>();

    public ShopService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String order(Bouquet bouquet) throws Exception {
        log.info("[Shop] Processing order..");

        Random random = new Random();
        int secondsOfShoppingProcess = 3;
        int successProbability = 90;

        Thread.sleep(random.nextInt(1000 * secondsOfShoppingProcess));

        if (random.nextInt(100) < successProbability) {
            UUID uuid = UUID.randomUUID();
            orders.put(uuid, OrderStatus.PROCESSING);

            //--------------------------------------------
            rabbitTemplate.convertAndSend("flowerQueue", uuid.toString());
            //--------------------------------------------

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
