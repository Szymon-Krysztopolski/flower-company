package com.flowercompany.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BouquetItem {
    private Flower flower;
    private int amount;
}
