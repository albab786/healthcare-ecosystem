# Stage 1: Compile the Java application using Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy the pom.xml first to download dependencies (improves Docker caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy your source code and package the production JAR file
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime container
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the compiled JAR from Stage 1 into this clean container
COPY --from=build /app/target/*.jar app.jar

# Expose port 8082 for the insurance service web API
EXPOSE 8082

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]