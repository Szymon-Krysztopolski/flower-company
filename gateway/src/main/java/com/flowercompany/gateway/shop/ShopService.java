package com.flowercompany.gateway.shop;

import com.flowercompany.gateway.dto.Bouquet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class ShopService {
    private static WebClient shopApiClient;

    @Autowired
    public ShopService(WebClient shopApiClient) {
        ShopService.shopApiClient = shopApiClient;
    }

    public String order(Bouquet bouquet) {
        log.info("[Gateway] Ordering the bouquet...");

        return shopApiClient
                .post()
                .uri("/api/v1/order")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(bouquet), Bouquet.class)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Error with your query!")
                .block();
    }

    public List<String> checkOrders() {
        log.info("[Gateway] Getting ids of all known orders...");

        return shopApiClient
                .get()
                .uri("/api/v1/orders")
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .onErrorReturn(List.of("Error with your query!"))
                .block();
    }
}
