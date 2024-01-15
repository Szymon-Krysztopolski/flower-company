package com.flowercompany.factory.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class Bouquet implements Serializable {
    UUID uuid;
    List<BouquetItem> bouquet;
}
