package com.techi.kafkastream.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.common.serialization.Serdes.String;
import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Autowired
    private Environment env;

    public KafkaStreamsConfiguration kafkaStreamsConfiguration(){
        final Map<String, Object> config = new HashMap<>();
        config.put(APPLICATION_ID_CONFIG, env.getProperty("spring.kafka.client-id"));
        config.put(BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
        config.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, String().getClass());
        config.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, String().getClass());
        config.put(DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
        return new KafkaStreamsConfiguration(config);
    }
}
