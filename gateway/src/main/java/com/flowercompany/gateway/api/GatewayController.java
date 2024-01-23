package com.flowercompany.gateway.api;

import com.flowercompany.gateway.domain.Bouquet;
import com.flowercompany.gateway.domain.Order;
import com.flowercompany.gateway.factory.FactoryService;
import com.flowercompany.gateway.factory.ws.Status;
import com.flowercompany.gateway.shop.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Placing an order to the store",
            description = "The order is placed in the store and is sent to the factory for processing.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created order, sent to factory for processing",
                    content = @Content(mediaType = "text/plain", schema = @Schema(example = "id123"))),
            @ApiResponse(responseCode = "400", description = "Error when ordering a bouquet",
                    content = @Content(mediaType = "text/plain", schema = @Schema(example = "Error with order!")))
    })
    @PostMapping("/order")
    public ResponseEntity<String> order(
            @Parameter(description = "The bouquet is a list of flowers and quantity ordered by the customer.")
            @RequestBody Bouquet bouquet) {
        log.info("[Gateway] Ordering the bouquet...");

        try {
            return ResponseEntity.ok(service.order(bouquet));
        } catch (Exception ex) {
            log.error("[Gateway] Error during ordering the bouquet", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Operation(summary = "Getting a list of all known orders",
            description = "Getting a list of all known orders. " +
                    "The order can be in the \"OK\" state which means that it is processing, " +
                    "or CANCELLED if the factory processing was terminated with an error.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of known orders",
                    content = @Content(schema = @Schema(example = ShopService.ORDERS_EXAMPLE_JSON)))
    })
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> checkOrders() {
        log.info("[Gateway] Checking of orders...");
        return ResponseEntity.ok(service.checkOrders());
    }

    @Operation(summary = "Getting order status and price",
            description = "Getting the order status from the factory. " +
                    "The order must first be placed by the store.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order status and price",
                    content = @Content(schema = @Schema(example = FactoryService.STATUS_EXAMPLE_OK_JSON))),
            @ApiResponse(responseCode = "404",
                    description = "The order with the given ID was not found",
                    content = @Content(schema = @Schema(example = FactoryService.STATUS_EXAMPLE_NOK_JSON)))
    })
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
