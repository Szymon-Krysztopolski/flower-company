package com.flowercompany.gateway.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(example = Bouquet.EXAMPLE_JSON)
public class Bouquet {
    private List<BouquetItem> bouquet;

    public static final String EXAMPLE_JSON = """
            {
                "bouquet": [
                    {
                        "flower": "RED_ROSE",
                        "amount": 1
                    },
                    {
                        "flower": "TULIP",
                        "amount": 2
                    }
                ]
            }
            """;
}
