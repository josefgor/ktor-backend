# First stage: build the jar
FROM gradle:8.4.0-jdk17 AS builder

WORKDIR /app

# Copy gradle files separately to leverage Docker cache
COPY gradle gradle
COPY gradlew .
#COPY build.gradle ./
COPY build.gradle.kts settings.gradle.kts ./

# Make gradlew executable
RUN chmod +x gradlew

# Pre-download dependencies (speeds up builds)
RUN ./gradlew dependencies

# Copy the rest of the source code
COPY . .

# Build the fat jar
RUN ./gradlew shadowJar

# Second stage: minimal runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the jar from the build stage
COPY --from=builder /app/build/libs/*-all.jar app.jar

EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]
