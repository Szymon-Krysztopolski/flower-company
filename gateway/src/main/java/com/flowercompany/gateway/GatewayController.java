package com.flowercompany.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GatewayController {
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Object object) {
        return ResponseEntity.ok("I got bouquet");
    }

    @GetMapping("/orders")
    public ResponseEntity<List<String>> checkOrders() {
        return ResponseEntity.ok(List.of("id1", "id2"));
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> status(@PathVariable String id) {
        return ResponseEntity.ok("example product with id: " + id);
    }
}