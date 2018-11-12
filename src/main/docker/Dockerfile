FROM openjdk:8-jdk-alpine
MAINTAINER Asuis
VOLUME /media/sys/docker/tmp
ADD crm-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]