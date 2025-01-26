package com.library.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(
            topics = "library-consumer-topic",
            groupId = "library-group"
    )
    public void listen(String message) {
        log.info("Message received: " + message);
    }
}
