package com.flowercompany.gateway.shop;

import com.flowercompany.gateway.domain.Bouquet;
import com.flowercompany.gateway.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
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
        return shopApiClient
                .post()
                .uri("/api/v1/order")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(bouquet), Bouquet.class)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Error with your order!")
                .block();
    }

    public List<Order> checkOrders() {
        return shopApiClient
                .get()
                .uri("/api/v1/orders")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(error ->
                                        Mono.error(new Exception(error))
                                )
                )
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {
                })
                .block();
    }
}
