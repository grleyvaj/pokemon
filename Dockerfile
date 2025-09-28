FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/generated-resources/xsd ./target/generated-resources/xsd

ENTRYPOINT ["java", "-jar", "app.jar"]
