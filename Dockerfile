FROM openjdk:8-jdk-alpine

LABEL maintainer= lcc.enrique.sanchez@gmail.com

VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/BankyPokem-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} banky-pokem.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "jar", "/banky-pokem.jar"]
