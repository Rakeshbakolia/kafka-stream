package com.techi.kafkastream.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techi.kafkastream.config.SerdeConfig;
import com.techi.kafkastream.constants.Constants;
import com.techi.kafkastream.model.Event;
import com.techi.kafkastream.model.aggregation.EventAggregation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class EventRoute {

    @Autowired
    private Environment env;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public void buildPipeline(StreamsBuilder builder){
        KStream<Long, Event> eventRoute = builder.stream(env.getProperty("kafka.event.topic"),
                        Consumed.with(Serdes.Long(), SerdeConfig.EventSerde()).withName("EventEntryProcessor"))
                .filter((k, v) -> Objects.nonNull(v));

        eventRoute.groupByKey()
                .aggregate(EventAggregation::new, this::calculatePopulation, Named.as("population-count"),
                        Materialized.<Long, EventAggregation , KeyValueStore<Bytes, byte[]>>as(
                                        Constants.POPULATION_STATE_STORE)
                                .withKeySerde(Serdes.Long())
                                .withValueSerde(SerdeConfig.populationSerde()));
    }

    private EventAggregation calculatePopulation(Long key, Event event, EventAggregation eventAggregation){
        eventAggregation.getPopulation().put(key, eventAggregation.getPopulation().getOrDefault(key, 0L)+1L);
        return eventAggregation;
    }
}
