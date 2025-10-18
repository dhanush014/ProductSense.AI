# Stage 1: Build your backend
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only backend files into the build context
COPY pom.xml .
COPY src ./src

# Package with Maven
RUN mvn clean package -DskipTests

# Stage 2: Package the JAR
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
