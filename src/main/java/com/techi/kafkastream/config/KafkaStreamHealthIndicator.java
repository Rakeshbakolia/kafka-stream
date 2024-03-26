package com.techi.kafkastream.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class KafkaStreamHealthIndicator implements HealthIndicator {

    @Autowired
    private StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    @Override
    public Health health() {
        KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();

        KafkaStreams.State streamState = kafkaStreams.state();

        // CREATED, RUNNING or REBALANCING
        if (streamState.isRunningOrRebalancing()) {
            //set details if you need one
            return Health.up().withDetails(Map.of("state", streamState.name())).build();
        }
        // ERROR, NOT_RUNNING, PENDING_SHUTDOWN,
        return Health.down().withDetail("status", kafkaStreams.state()).build();
    }
}
