package com.flowercompany.factory.status;

import com.flowercompany.factory.status.ws.Status;
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
