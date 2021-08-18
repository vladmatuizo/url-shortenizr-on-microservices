FROM openjdk:8-alpine
ADD target/config-server-*.jar /config-server.jar
ENV RABBITMQ_HOST=rabbit
ENV CONFIG_REPO_URL="https://gitlab.exposit.com/vladislav.matujzo/url-shortenizr-config.git"
EXPOSE 8888
ENTRYPOINT ["java","-jar","config-server.jar"]
