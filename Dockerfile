FROM openjdk:21
MAINTAINER cardapi.com
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","./app.jar"]