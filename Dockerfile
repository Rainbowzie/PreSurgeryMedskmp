# Start from Maven + JDK image
FROM maven:3.9.2-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy everything into container
COPY . .

# Build the project
RUN mvn -DskipTests=true package

# Expose port (matches SparkJava default)
EXPOSE 4567

# Run your app
CMD ["java", "-jar", "target/petmed-api-1.0.0-jar-with-dependencies.jar"]
