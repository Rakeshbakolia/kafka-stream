package com.techi.kafkastream.model;

import lombok.Data;

@Data
public class Event {
    private String birthCertificateNumber;
    private String name;
    private Long birtDate;
    private Long deathDate;
}
