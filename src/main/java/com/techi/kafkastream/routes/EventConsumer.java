package com.techi.kafkastream.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techi.kafkastream.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@EnableKafka
@Component
@Slf4j
public class EventConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Autowired
    KafkaTemplate<Long, Event> kTemplate;

    @KafkaListener(topics = "${kafka.basic.event.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOldEventTopic(@Payload String messages) {
        try {
            log.info("Received Event : {}", messages);
            Event event = objectMapper.readValue(messages, Event.class);
            kTemplate.send(Objects.requireNonNull(env.getProperty("kafka.event.topic")), event.getDate(), event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
