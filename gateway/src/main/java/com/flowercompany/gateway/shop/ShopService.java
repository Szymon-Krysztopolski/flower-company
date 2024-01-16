package com.flowercompany.gateway.shop;

import com.flowercompany.gateway.domain.Bouquet;
import com.flowercompany.gateway.domain.Order;
import com.flowercompany.gateway.exception.OrderException;
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
                .onErrorResume(throwable -> {
                    throw new OrderException(throwable);
                })
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

    public static final String ORDERS_EXAMPLE_JSON = """  
            [
                {
                  "id": "id1",
                  "code": "OK"
                },
                {
                  "id": "id2",
                  "code": "CANCELLED"
                },
                {
                  "id": "id3",
                  "code": "OK"
                }
            ]
            """;
}
