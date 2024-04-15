package com.techi.kafkastream.config;

import com.techi.kafkastream.model.Event;
import com.techi.kafkastream.model.aggregation.EventAggregation;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class SerdeConfig extends Serdes.WrapperSerde<Event> {

    public SerdeConfig() {
        super(new JsonSerializer<>(), new JsonDeserializer<>());
    }

    public static Serde<Event> EventSerde(){
        final JsonSerializer<Event> eventJsonSerializer = new JsonSerializer<>();
        final JsonDeserializer<Event> eventJsonDeserializer = new JsonDeserializer<>(Event.class);
        return Serdes.serdeFrom(eventJsonSerializer, eventJsonDeserializer);
    }

    public static Serde<EventAggregation> populationSerde() {
        final JsonSerializer<EventAggregation> polpulationJsonSerializer = new JsonSerializer<>();
        final JsonDeserializer<EventAggregation> polpulationJsonDeserializer = new JsonDeserializer<>(EventAggregation.class);
        return Serdes.serdeFrom(polpulationJsonSerializer, polpulationJsonDeserializer);
    }

}
