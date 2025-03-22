FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY ./ /home/app
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/UsedCarInventory.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]