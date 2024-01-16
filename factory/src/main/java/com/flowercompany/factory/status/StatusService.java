package com.flowercompany.factory.status;

import com.flowercompany.shared.Bouquet;
import com.flowercompany.factory.exception.FactoryException;
import com.flowercompany.factory.status.ws.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class StatusService {
    Map<String, Status> statusMap = new HashMap<>();

    public Status getStatus(String id) {
        return statusMap.get(id);
    }

    @RabbitListener(queues = "flowerQueue")
    public void listen(Bouquet in) throws Exception {
        log.info("[Factory] Message read from flowerQueue: {}", in.toString());
        Random random = new Random();
        int secondsOfShoppingProcess = 3;
        int successProbability = 90;

        Thread.sleep(random.nextInt(1000 * secondsOfShoppingProcess));
        if (random.nextInt(100) < successProbability) {
            log.info("[Factory] New bouquet has been created");

            Status status = new Status();
            final String id = in.getUuid().toString();
            status.setId(id);
            status.setCode("OK");
            status.setPrice(50); // todo calculations

            statusMap.put(id, status);
        } else {
            log.error("[Factory] Error during processing!");
            throw new FactoryException();
        }
    }
}
