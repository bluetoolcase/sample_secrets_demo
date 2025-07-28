# Use Maven with OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-21 AS builder
# Set working directory inside the container
WORKDIR /app
# Copy project files
COPY ./java_saas/ .
# Package the application (skip tests if needed)
RUN mvn clean package -DskipTests

# Use lightweight JDK base for runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copy compiled jar from builder stage
COPY --from=builder /app/target/*.jar ./
COPY --chmod=777 ./infrastructure/Java_Saas/docker-entrypoint.sh ./
# Expose port (adjust as needed)
EXPOSE 8091
# Run the app
ENTRYPOINT ["./docker-entrypoint.sh"]
