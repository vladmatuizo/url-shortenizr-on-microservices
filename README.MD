## Shortenizr - demo microservice project

#### For starting application manually you need:
 - Download and install RabbitMQ - https://www.rabbitmq.com/download.html
 - MySQL installed on your local machine
 - Build parent-reactor with Maven `mvn clean package`
 - Download Zipkin - https://zipkin.io/pages/quickstart.html
 - Start `config-server`
 - Start `eureka-naming-server`
 - Start Zipkin and other services

**Services would work on:**
 - Config server - http://localhost:8888
 - Eureka - http://localhost:8761
 - Zipkin - http://localhost:9411
 - Zuul - http://localhost:8765
 - Link statistic service - http://localhost:8100
 - Shortenizr - http://localhost:8000

#### For starting application on Docker engine you need:
 Run `build-with-docker-compose.(bat/sh)` in **bin** folder or
 - build parent-reactor with Maven `mvn clean package`
 - build and up docker containers: `docker-compose build && docker-compose up`
