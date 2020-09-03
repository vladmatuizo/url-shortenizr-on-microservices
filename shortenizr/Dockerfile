FROM openjdk:8-alpine
ADD target/shortenizr-*.jar /shortenizr.jar
ENV CONFIG_PROFILE=dev
ENV CONFIG_SERVER_URI="http://config-server:8888"
EXPOSE 8000
ENTRYPOINT ["java","-jar","shortenizr.jar"]
