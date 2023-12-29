package com.flowercompany.gateway;

import com.flowercompany.gateway.dto.Bouquet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GatewayController {
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Bouquet bouquet) {
        return ResponseEntity.ok("I got bouquet with those items: " + bouquet.toString());
    }

    @GetMapping("/receive/{id}")
    public ResponseEntity<String> receive(@PathVariable String id) {
        return ResponseEntity.ok("example product with id: " + id);
    }
}
