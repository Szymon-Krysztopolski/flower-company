package com.flowercompany.factory.status;

import com.flowercompany.factory.exception.FactoryException;
import com.flowercompany.factory.status.ws.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class StatusService {
    Map<String, Status> statusMap = new HashMap<>();

    public Status getStatus(String id) {
        return statusMap.get(id);
    }

    @RabbitListener(queues = "flowerQueue")
    public void listen(String in) throws Exception {
        log.info("[Factory] Message read from myQueue: {}", in);
        Random random = new Random();
        int secondsOfShoppingProcess = 3;
        int successProbability = 90;

        Thread.sleep(random.nextInt(1000 * secondsOfShoppingProcess));
        if (random.nextInt(100) < successProbability) {
            log.error("[Factory] New bouquet has been created");

            Status status = new Status();
            status.setId(in);
            status.setCode("OK");
            status.setPrice(50); // todo calculations

            statusMap.put(in, status);
        } else {
            log.error("[Factory] Error during processing!");
            throw new FactoryException();
        }
    }
}
