# ===== Stage 1: Build =====
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only pom.xml first for dependency caching
COPY pom.xml .
# Copy the source code
COPY src ./src

# Package the application without running tests
RUN mvn -DskipTests=true package

# ===== Stage 2: Run =====
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/petmed-api-1.0.0-jar-with-dependencies.jar app.jar

# Expose SparkJava's default port
EXPOSE 4567

# Run the application
CMD ["java", "-jar", "app.jar"]

