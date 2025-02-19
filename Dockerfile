# Use a base image with JDK 21 for the build stage
FROM eclipse-temurin:21-jdk-jammy as builder

# Set the working directory inside the container
WORKDIR /app

# Copy all project files to the container
COPY . .

# Ensure the Maven wrapper has executable permissions
RUN chmod +x mvnw

# (Optional) Print Maven version for debugging
RUN ./mvnw --version

# Build the project with Maven in debug mode (verbose logging)
RUN ./mvnw clean package -DskipTests -X && ls -l /app/target

# Use a runtime image with JDK 21 to run the application
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory for the runtime container
WORKDIR /app

# Copy the built JAR file from the builder stage to the runtime image
COPY --from=builder /app/target/*.jar app.jar

# (Optional) Debug: List files in the runtime container
RUN echo "Contents of /app:" && ls -l /app

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
