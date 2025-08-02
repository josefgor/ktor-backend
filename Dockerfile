FROM gradle:8.4.0-jdk17 as builder
WORKDIR /app
COPY . .
RUN ./gradlew shadowJar

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/todo2-all.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
