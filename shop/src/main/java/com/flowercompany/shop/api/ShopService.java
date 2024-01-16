package com.flowercompany.shop.api;

import com.flowercompany.shared.Bouquet;
import com.flowercompany.shop.domain.Order;
import com.flowercompany.shop.domain.OrderStatus;
import com.flowercompany.shop.exception.OrderException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ShopService {
    private final RabbitTemplate rabbitTemplate;
    @Getter
    private final List<Order> orders = new ArrayList<>();

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
            orders.add(new Order(uuid.toString(), OrderStatus.OK));
            bouquet.setUuid(uuid);

            rabbitTemplate.convertAndSend("flowerQueue", bouquet);

            return uuid.toString();
        } else {
            log.error("[Shop] Error with query!");
            throw new OrderException();
        }
    }
}
