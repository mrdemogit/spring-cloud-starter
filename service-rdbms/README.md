# Example of service
Basic Rest Webservice with blocking jdbc 

## Requirements
1. Consul Service
2. Kafka Broker
3. Java 1.11.x
4. Gradle
5. ?Docker for consul, kafka

## Structure
- userprofile-api - API module
- userprofile-service - Service module

## Minimal Steps to Setup
Set Consul Service:
1. Run Docker
2. docker run -d -p 8500:8500 -e CONSUL_CLIENT_INTERFACE='eth0' -e CONSUL_BIND_INTERFACE='eth0' --restart=always --name consul consul:latest
4. docker run -d -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=127.0.0.1 --env ADVERTISED_PORT=9092 --name kafka spotify/kafka

Create user-profile-topic in Kafka Broker: 
1. docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic user-profile-topic

Run Service in terminal:
1. gradle build
2. java -jar example-service/build/libs/userprofile-service-0.2.0.jar
3. check if service is running: http://localhost:8500/

## Set Shared Attribute with Consul
1. In consul go to Key/Value section [http://localhost:8500/ui/dc1/kv](http://localhost:8500/ui/dc1/kv)
and create folder `/example-service/shared/attribute` and set value `someAttribute`.
2. Check if attribute is set correctly: [http://localhost:8100/userprofiles/attribute](http://localhost:8100/userprofiles/attribute)
