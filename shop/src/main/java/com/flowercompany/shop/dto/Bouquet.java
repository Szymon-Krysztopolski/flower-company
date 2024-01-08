package com.flowercompany.shop.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Bouquet {
    List<BouquetItem> bouquet;

    @Override
    public String toString() {
        return bouquet
                .stream()
                .map(item -> String.format("%s: %d",
                        item.getFlower(),
                        item.getAmount()))
                .collect(Collectors.toSet())
                .toString();
    }
}
