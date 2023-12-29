package com.flowercompany.factory.status;

import com.flowercompany.factory.status.ws.GetResponse;
import com.flowercompany.factory.status.ws.GetStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StatusEndpoint {
    private final StatusService service;

    public StatusEndpoint(StatusService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = "http://ws.status.factory.flowercompany.com", localPart = "getStatus")
    @ResponsePayload
    public GetResponse getResponse(@RequestPayload GetStatus getStatus) {
        GetResponse response = new GetResponse();
        response.setStatus(service.getStatus(getStatus.getId()));
        return response;
    }
}
