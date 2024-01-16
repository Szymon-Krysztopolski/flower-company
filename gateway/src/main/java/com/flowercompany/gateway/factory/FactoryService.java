package com.flowercompany.gateway.factory;

import com.flowercompany.gateway.factory.ws.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {
    private final StatusClient statusClient;

    @Autowired
    public FactoryService(StatusClient statusClient) {
        this.statusClient = statusClient;
    }

    public Status checkOrderStatus(String id) {
        return statusClient.getStatus(id).getStatus();
    }

    public static final String STATUS_EXAMPLE_OK_JSON = """
            {
              "id": "id123",
              "code": "OK",
              "price": 50
            }
            """;
    public static final String STATUS_EXAMPLE_NOK_JSON = """
            {
              "id": "id123",
              "code": "NOK"
            }
            """;
}
