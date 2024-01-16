package com.flowercompany.gateway.factory;

import com.flowercompany.gateway.factory.ws.GetStatusRequest;
import com.flowercompany.gateway.factory.ws.GetStatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
public class StatusClient extends WebServiceGatewaySupport {

    @Value("${factory.endpoint}")
    private String factoryEndpoint;

    public GetStatusResponse getStatus(String id) {
        GetStatusRequest request = new GetStatusRequest();
        request.setId(id);


        GetStatusResponse response = (GetStatusResponse) getWebServiceTemplate().marshalSendAndReceive(
                factoryEndpoint + "/",
                request,
                new SoapActionCallback("")
        );

        return response;
    }
}
