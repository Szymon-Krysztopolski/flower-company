package com.flowercompany.factory.status;

import com.flowercompany.factory.status.ws.GetStatusResponse;
import com.flowercompany.factory.status.ws.GetStatusRequest;
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

    @PayloadRoot(namespace = "http://ws.status.factory.flowercompany.com", localPart = "getStatusRequest")
    @ResponsePayload
    public GetStatusResponse getStatus(@RequestPayload GetStatusRequest request) {
        GetStatusResponse response = new GetStatusResponse();
        response.setStatus(service.getStatus(request.getId()));
        return response;
    }
}
