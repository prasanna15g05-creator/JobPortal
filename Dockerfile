# Use lightweight Java runtime
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar file into container
COPY target/OnlineQuiz-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Railway uses this)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]