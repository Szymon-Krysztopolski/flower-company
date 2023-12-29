package com.flowercompany.shop;

import com.flowercompany.shop.dto.Bouquet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShopController {
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Bouquet bouquet) {
        return ResponseEntity.ok("I got bouquet: " + bouquet.toString());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<String>> checkOrders() {
        return ResponseEntity.ok(List.of("id1", "id2"));
    }
}
