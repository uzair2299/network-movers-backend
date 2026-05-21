# Stage 1: Build the application
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy source and build using Maven (skip tests for faster builds)
COPY . .
RUN mvn -B clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the fat/uber jar produced by the build stage
COPY --from=build /app/target/*.jar app.jar

# Application port (adjust if your app uses a different port)
EXPOSE 8085

# Entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
