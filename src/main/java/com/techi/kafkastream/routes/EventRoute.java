package com.techi.kafkastream.routes;

import com.techi.kafkastream.config.SerdeConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class EventRoute {

    @Autowired
    private Environment env;

    @Autowired
    public void buildPipeline(StreamsBuilder builder){
        builder.stream(env.getProperty("kafka.event.topic"),
                Consumed.with(Serdes.String(),Serdes.String()).withName("EventEntryProcessor"))
                .filter((k,v) -> Objects.nonNull(v))
                .peek((k,v)->{
                    log.info("key : {}, val : {}", k, v);
                });
    }
}
