package com.flowercompany.factory.status;

import com.flowercompany.factory.status.ws.Status;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatusService {
    Map<String, Status> statusMap = new HashMap<>();

    public Status getStatus(String id) {
        return statusMap.get(id);
    }
}
