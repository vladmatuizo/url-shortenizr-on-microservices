FROM openjdk:8-alpine
ADD target/zuul-api-gateway-server-*.jar /zuul-api-gateway-server.jar
ENV CONFIG_PROFILE=devops
ENV CONFIG_SERVER_URI="http://config-server:8888"
EXPOSE 8765
ENTRYPOINT ["java","-jar","zuul-api-gateway-server.jar"]
