# Stage 1: Build the application
FROM gradle:8.7-jdk21-alpine AS build

WORKDIR /app

# Copy Gradle wrapper and config
COPY . /app/

# Build the fat JAR
RUN ./gradlew :server:shadowJar

# Stage 2: Run the application
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/server/build/libs/server-all.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]