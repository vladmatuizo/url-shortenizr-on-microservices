FROM openjdk:8-alpine
ADD target/links-statistic-service-*.jar /links-statistic-service.jar
ENV CONFIG_PROFILE=devops
ENV CONFIG_SERVER_URI="http://config-server:8888"
EXPOSE 8100
ENTRYPOINT ["java","-jar","links-statistic-service.jar"]
