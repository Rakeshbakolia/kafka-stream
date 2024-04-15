
## Demonstration of the functionality of kafka-producer, kafka-consumer, kafka-streams, local and global state store.

### What is in this project

1.) Send messages in kafka using kafka-template and kafka-producer configuration.

2.) Kafka-consumer configuration and polling data in kafka-consumer using @KafkaListener.

3.) Kafka-stream configuration and stream data from an event.

4.) Creating local-state-store and store aggregated data in the store.

5.) Global state store to store data of all the partions of local-state-store.

6.) Querying the data from global state store.

7.) Creation of custom Serdes.

## Prerequisite
download kafka
download link
- [download-kafka](https://kafka.apache.org/downloads)
command to run kafka
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```
this will run kafka on the machine

## Installation

Clone/Fork the project and run 

```bash
  mvn clean install -f pom.xml -DskipTests
  java -jar /target/kafka-stream-0.0.1-SNAPSHOT.jar
```
    
## Authors

- [github](https://github.com/Rakeshbakolia)
- [linkedin](https://www.linkedin.com/in/rakesh-bakolia-8b9842144/)

## API Reference

#### Get all data of population global-state-store

```http
  GET /api/v1/store/population
  GET /actuator
```

