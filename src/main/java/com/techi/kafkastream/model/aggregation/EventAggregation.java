package com.techi.kafkastream.model.aggregation;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EventAggregation {
    private Map<Long, Long> population = new HashMap<>();
}
