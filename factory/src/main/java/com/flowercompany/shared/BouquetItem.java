package com.flowercompany.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class BouquetItem implements Serializable {
    private Flower flower;
    private int amount;
}
