package com.flowercompany.gateway.api;

import com.flowercompany.gateway.domain.Bouquet;
import com.flowercompany.gateway.domain.Order;
import com.flowercompany.gateway.factory.ws.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("[Gateway] Ordering the bouquet...");

        try {
            return ResponseEntity.ok(service.order(bouquet));
        } catch (Exception ex) {
            log.error("[Gateway] Error during ordering the bouquet", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> checkOrders() {
        log.info("[Gateway] Checking of orders...");
        return ResponseEntity.ok(service.checkOrders());
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> status(@PathVariable String id) {
        log.info("[Gateway] Checking status of order with id: {}...", id);

        final Status status = service.checkOrderStatus(id);
        return (status == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorStatus(id))
                : ResponseEntity.ok(status));
    }

    private Status errorStatus(String errorId) {
        Status errorStatus = new Status();
        errorStatus.setId(errorId);
        errorStatus.setCode("NOK");
        return errorStatus;
    }
}
