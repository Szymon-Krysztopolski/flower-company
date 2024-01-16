package com.flowercompany.gateway.api;

import com.flowercompany.gateway.domain.Bouquet;
import com.flowercompany.gateway.domain.Order;
import com.flowercompany.gateway.factory.ws.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://localhost:3000"})
public class GatewayController {
    private final GatewayService service;

    public GatewayController(GatewayService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Bouquet bouquet) {
        return ResponseEntity.ok(service.order(bouquet));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> checkOrders() {
        return ResponseEntity.ok(service.checkOrders());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> status(@PathVariable String id) {
        return ResponseEntity.ok(service.checkOrderStatus(id));
    }
}
