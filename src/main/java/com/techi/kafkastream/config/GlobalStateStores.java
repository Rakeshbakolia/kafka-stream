package com.techi.kafkastream.config;

import com.techi.kafkastream.constants.Constants;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalStateStores {

    @Value("${spring.application.name}")
    private String kafkaClientId;

    private static final String CHANGE_LOG = "changelog";

    @Autowired
    public void globalStateStore(StreamsBuilder streamsBuilder) {
        streamsBuilder.globalTable(kafkaClientId +"-"+ Constants.POPULATION_STATE_STORE +"-"+CHANGE_LOG, Consumed.with(Serdes.Long(), SerdeConfig.populationSerde()), Materialized.as(Constants.POPULATION_GLOBAL_STATE_STORE));
    }
}
