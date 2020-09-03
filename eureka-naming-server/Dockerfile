FROM openjdk:8-alpine
ADD target/eureka-naming-server-*.jar /eureka-naming-server.jar
ENV CONFIG_PROFILE=dev
ENV CONFIG_SERVER_URI="http://config-server:8888"
EXPOSE 8761
ENTRYPOINT ["java","-jar","eureka-naming-server.jar"]
