package com.flowercompany.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private String id;
    private OrderStatus code;

    public void cancel() {
        this.code = OrderStatus.CANCELLED;
    }
}
