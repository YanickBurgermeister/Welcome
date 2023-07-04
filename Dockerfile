FROM maven:3.8.5-openjdk-17 AS mvn-builder
WORKDIR /app
COPY . .
RUN mvn clean package
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=mvn-builder /app/target/*.jar /app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/*.jar" ]