# Example of service
Basic Rest Webservice with blocking jdbc 

## Requirements
1. Consul Service
2. Java 1.11.x
3. Gradle
4. ?Docker for consul

## Structure
- example-api - API module
- example-service - Service module

## Minimal Steps to Setup
Set Consul Service:
1. Run Docker
2. docker run -d -p 8500:8500 -e CONSUL_CLIENT_INTERFACE='eth0' -e CONSUL_BIND_INTERFACE='eth0' --restart=always --name consul consul:latest

Run Service in terminal:
1. gradle build
2. java -jar example-service/build/libs/example-service-0.1.0.jar
3. check if service is running: http://localhost:8500/

## Set Shared Attribute with Consul
1. In consul go to Key/Value section [http://localhost:8500/ui/dc1/kv](http://localhost:8500/ui/dc1/kv)
and create folder `/example-service/shared/attribute` and set value `someAttribute`.
2. Check if attribute is set correctly: [http://localhost:8100/userprofiles/attribute](http://localhost:8100/userprofiles/attribute)
