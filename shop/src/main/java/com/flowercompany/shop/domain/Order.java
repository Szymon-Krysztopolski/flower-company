package com.flowercompany.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Order {
    private String id;
    private OrderStatus code;
}
