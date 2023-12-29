package com.flowercompany.factory.ws;

import com.soap_example.GetResponse;
import com.soap_example.GetStatus;
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

    @PayloadRoot(namespace = "http://soap-example.com/", localPart = "getStatus")
    @ResponsePayload
    public GetResponse getResponse(@RequestPayload GetStatus getStatus) {
        GetResponse response = new GetResponse();
        response.setStatus(service.getStatus(getStatus.getId()));
        return response;
    }
}
