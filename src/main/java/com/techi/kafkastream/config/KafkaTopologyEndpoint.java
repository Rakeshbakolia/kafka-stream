package com.techi.kafkastream.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Endpoint(id = "topology")
@Component
public class KafkaTopologyEndpoint {

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @ReadOperation
    public String topology(){
        return Objects.requireNonNull(factoryBean.getTopology()).describe().toString();
    }
}
