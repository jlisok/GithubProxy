# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jre-alpine
ADD app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]