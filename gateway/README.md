# Example of gateway
Gateway on Spring Framework 5, Project Reactor and Spring Boot 2 using non-blocking APIs

## Requirements
1. Consul Service
2. Java 1.11.x
3. Gradle
4. ?Docker for consul

## Structure
example-gateway - Gateway implementation

## Minimal Steps to Setup
Set Consul Service:
1. Run Docker
2. docker run -d -p 8500:8500 -e CONSUL_CLIENT_INTERFACE='eth0' -e CONSUL_BIND_INTERFACE='eth0' --restart=always --name consul consul:latest

Run Service in terminal:
1. gradle build
2. java -jar example-gateway/build/libs/example-gateway-0.1.0.jar
3. check if service is running: http://localhost:8500/

## How to test it

```
1. go to root folder and run service (http://localhost:8100)
2. create resources through gateway

POST localhost:8000/userprofiles
body:
{
	"firstName": "John",
	"lastName": "Test"
}

3. get resource through gateway

GET localhost:8000/userprofiles/Test
```

