# BUILD

FROM gradle:8.5.0-jdk21-alpine AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN gradle build
COPY src/ src/
RUN gradle build --scan

# DEPLOY

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/TaxiApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD java -jar app.jar