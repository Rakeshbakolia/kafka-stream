package com.techi.kafkastream.controller;

import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/store")
public class StateStoreController {

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @GetMapping("/population")
    public ResponseEntity<?> getPopulationStoreData(){
        ReadOnlyKeyValueStore<Object, Object> store = factoryBean.getKafkaStreams().store(StoreQueryParameters.fromNameAndType("population-store", QueryableStoreTypes.keyValueStore()));
        return new ResponseEntity<>(store.all(), HttpStatus.OK);
    }
}
