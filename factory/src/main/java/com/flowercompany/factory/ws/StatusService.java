package com.flowercompany.factory.ws;

import com.soap_example.Status;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    public Status getStatus(String id) {
        Status status = new Status();

        // TODO
        status.setCode("OK");
        status.setPrice(50);
        return status;
    }
}
