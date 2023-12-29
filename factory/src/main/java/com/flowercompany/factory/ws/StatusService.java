package com.flowercompany.factory.ws;

import com.flowercompany.factory.ws.status.Response;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    public Response getResponse(String id) {
        Response response = new Response();

        // TODO
        response.setStatus("OK");
        response.setPrice(50);
        return response;
    }
}
