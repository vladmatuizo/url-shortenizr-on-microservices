FROM openjdk:8-alpine
ADD target/zuul-api-gateway-server-*.jar /zuul-api-gateway-server.jar
ENV CONFIG_PROFILE=dev
ENV CONFIG_SERVER_URI="http://config-server:8888"
EXPOSE 8000
ENTRYPOINT ["java","-jar","zuul-api-gateway-server.jar"]
