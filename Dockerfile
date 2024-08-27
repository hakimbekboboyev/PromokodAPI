# Use a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/PromokodAPI-0.0.1-SNAPSHOT.jar promo.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 9090

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "promo.jar"]
