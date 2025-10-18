# Stage 1: build your backend
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set a working directory for all subsequent commands
WORKDIR /app

# Copy only the files needed for building the backend
COPY productsenseai-backend/pom.xml ./productsenseai-backend/
COPY productsenseai-backend/src ./productsenseai-backend/src

# Move into the backend folder and build
WORKDIR /app/productsenseai-backend
RUN mvn clean package -DskipTests

# Stage 2: package the jar
FROM eclipse-temurin:21-jdk

# Copy the built jar from the build stage
COPY --from=build /app/productsenseai-backend/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Launch the application
ENTRYPOINT ["java","-jar","/app.jar"]
