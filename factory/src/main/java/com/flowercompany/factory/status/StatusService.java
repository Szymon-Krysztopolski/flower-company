package com.flowercompany.factory.status;

import com.flowercompany.shared.Bouquet;
import com.flowercompany.factory.exception.FactoryException;
import com.flowercompany.factory.status.ws.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class StatusService {
    private final RabbitTemplate rabbitTemplate;
    Map<String, Status> statusMap = new HashMap<>();

    public StatusService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Status getStatus(String id) {
        return statusMap.get(id);
    }

    @RabbitListener(queues = "flowerQueue")
    public void listen(Bouquet in) {
        log.info("[Factory] Message read from flowerQueue: {}", in.toString());

        try {
            orderProcessing(in);
        } catch (Exception ex) {
            log.error("[Factory] Error during processing!", ex);
            rabbitTemplate.convertAndSend("errorQueue", in.getUuid().toString());
        }
    }

    private void orderProcessing(Bouquet bouquet) throws Exception {
        Random random = new Random();
        int secondsOfShoppingProcess = 3;
        int successProbability = 50;

        Thread.sleep(random.nextInt(1000 * secondsOfShoppingProcess));
        if (random.nextInt(100) < successProbability) {
            log.info("[Factory] New bouquet has been created");

            Status status = new Status();
            final String id = bouquet.getUuid().toString();
            status.setId(id);
            status.setCode("OK");
            status.setPrice(random.nextInt(50) + 50);

            statusMap.put(id, status);
        } else {
            throw new FactoryException();
        }
    }
}
