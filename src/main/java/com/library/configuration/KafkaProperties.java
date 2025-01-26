package com.library.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spring.kafka")
@Component
public class KafkaProperties {

    private String bootstrapServers;
    private Consumer consumer;
    private Producer producer;

    @Data
    public static class Consumer {
        private String groupId;
        private String autoOffsetReset;
        private String topic;
    }

    @Data
    public static class Producer {
        private String keySerializer;
        private String valueSerializer;
        private String topic;
    }
}
