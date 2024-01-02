package com.flowercompany.gateway.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GatewayController {
    private final GatewayService service;

    public GatewayController(GatewayService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Object object) {
        return ResponseEntity.ok(service.order(object));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<String>> checkOrders() {
        return ResponseEntity.ok(service.checkOrders());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> status(@PathVariable String id) {
        return ResponseEntity.ok(service.checkOrderStatus(id));
    }
}
