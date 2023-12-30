package com.flowercompany.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class ProductStatus{
    private String id;
    private int price;
    private String status;

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequestMapping("/api/v1")
public class GatewayController {
    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody Object object) {
        return ResponseEntity.ok("id1");
    }

    @GetMapping("/orders")
    public ResponseEntity<List<String>> checkOrders() {
        return ResponseEntity.ok(List.of("id1", "id2"));
    }
    @GetMapping("/status/{id}")
    public ResponseEntity<ProductStatus> status(@PathVariable String id) {
        ProductStatus productStatus = new ProductStatus();
        productStatus.setId(id);
        productStatus.setPrice(100);
        productStatus.setStatus("everything is ok");

        return ResponseEntity.ok(productStatus);
    }

}
