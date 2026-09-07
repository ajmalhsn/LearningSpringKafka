FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon
COPY src ./src
RUN ./gradlew bootJar -x test --no-daemon
COPY --from=build /app/build/libs/*.jar app.jar