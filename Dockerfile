# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-alpine
ADD app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]