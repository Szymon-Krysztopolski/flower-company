package com.flowercompany.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GatewayController {
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Object object) {
        return ResponseEntity.ok("I got bouquet");
    }

    @GetMapping("/receive/{id}")
    public ResponseEntity<String> receive(@PathVariable String id) {
        return ResponseEntity.ok("example product with id: " + id);
    }
}
