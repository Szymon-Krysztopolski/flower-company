package com.flowercompany.shop.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class Bouquet implements Serializable {
    UUID uuid;
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
