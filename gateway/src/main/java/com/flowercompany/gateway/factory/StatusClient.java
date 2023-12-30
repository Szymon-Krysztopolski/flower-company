package com.flowercompany.gateway.factory;

import com.flowercompany.gateway.factory.ws.GetStatusRequest;
import com.flowercompany.gateway.factory.ws.GetStatusResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
public class StatusClient extends WebServiceGatewaySupport {

    public GetStatusResponse getStatus(String id) {
        GetStatusRequest request = new GetStatusRequest();
        request.setId(id);


        GetStatusResponse response = (GetStatusResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://localhost:8083/ws/",
                request,
                new SoapActionCallback("")
        );

        return response;
    }
}
