# Stage 1: Build with JDK 24
FROM eclipse-temurin:24-jdk AS jdk24

# Install Maven manually
RUN \
  apt-get update && \
  apt-get install -y maven && \
  rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Packaging with JDK 24
FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY --from=jdk24 /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
