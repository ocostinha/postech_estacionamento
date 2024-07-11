FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/estacionamento-app.jar estacionamento-app.jar

ENV PORT 8080

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "estacionamento-app.jar"]
