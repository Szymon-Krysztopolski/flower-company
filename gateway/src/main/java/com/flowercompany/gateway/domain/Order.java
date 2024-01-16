package com.flowercompany.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private String id;
    private OrderStatus code;
}
