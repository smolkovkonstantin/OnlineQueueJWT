FROM maven:3.8.1-openjdk-17 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean install

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/OnlineQueueJWT-0.0.1.jar /app/OnlineQueueJWT-0.0.1.jar

COPY src/main/resources/application-dev.yml /app/application-dev.yml

CMD ["java", "-jar", "-Dspring.config.location=/app/application-dev.yml", "OnlineQueueJWT-0.0.1.jar"]