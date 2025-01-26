package com.library.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener {

    @KafkaListener(topics = "library-consumer-topic", groupId = "library-group")
    public void listen(String message) {
        log.info("Message received: {}", message);
    }
}
