package com.techi.kafkastream.config;

import com.techi.kafkastream.model.Event;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class SerdeConfig extends Serdes.WrapperSerde<Event> {

    public SerdeConfig() {
        super(new JsonSerializer<>(), new JsonDeserializer<>());
    }

    public static Serde<Event> EventSerde(){
        JsonSerializer<Event> eventJsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Event> eventJsonDeserializer = new JsonDeserializer<>();
        return Serdes.serdeFrom(eventJsonSerializer, eventJsonDeserializer);
    }
}
