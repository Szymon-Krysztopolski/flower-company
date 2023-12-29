package com.flowercompany.factory.ws;

import com.flowercompany.factory.ws.status.GetResponse;
import com.flowercompany.factory.ws.status.GetStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class StatusEndpoint {
    private final StatusService service;

    public StatusEndpoint(StatusService service) {
        this.service = service;
    }

    public GetResponse getResponse(@RequestPayload GetStatus getStatus) {
        GetResponse response = new GetResponse();
        response.setResponse(service.getResponse(getStatus.getId()));
        return response;
    }
}
