# syntax=docker/dockerfile:1
FROM gradle:8.4.0-jdk17 AS builder

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and configuration
COPY ../gradlew /app/
COPY ../gradle /app/gradle/
COPY ../gradle.properties /app/
COPY ../settings.gradle.kts /app/
COPY ../build.gradle.kts /app/

# Copy project modules
COPY ../composeApp /app/composeApp/
COPY ../shared /app/shared/

# Build the WASM distribution -- FIXME this is not working...
# RUN ./gradlew :composeApp:wasmJsBrowserDistribution --no-daemon --stacktrace

# Use Nginx to serve the built application
FROM nginx:alpine
COPY --from=builder /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
