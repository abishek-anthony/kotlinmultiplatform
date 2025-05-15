# syntax=docker/dockerfile:1
FROM gradle:8.7-jdk21-alpine AS build

WORKDIR /app
COPY . /app/

#RUN ./gradlew :composeApp:wasmJsBrowserDistribution --no-daemon --stacktrace

FROM nginx:alpine
COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]