# Etapa 1: build da aplicação usando Maven e JDK 11
FROM maven:3.8.5-eclipse-temurin-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagem final apenas com o JAR
FROM eclipse-temurin:11-jdk
WORKDIR /app
COPY --from=builder /app/target/JMTech-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
