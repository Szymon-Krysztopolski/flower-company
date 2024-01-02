package com.flowercompany.shop.api;

import com.flowercompany.shop.dto.Bouquet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ShopController {
    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Bouquet bouquet) {
        try {
            return ResponseEntity.ok(service.order(bouquet.getBouquet()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something goes wrong...");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<UUID>> checkOrders() {
        return ResponseEntity.ok(service.getOrders());
    }
}
