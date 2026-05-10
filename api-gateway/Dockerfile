# Build stage: use Maven with JDK11 to compile the application
FROM maven:3.8.6-jdk-11 AS build
WORKDIR /workspace/app

# Copy maven configuration and source code
COPY pom.xml ./
COPY src ./src

# Resolve dependencies and build the jar (skip tests for faster builds; remove -DskipTests in CI if needed)
RUN mvn -B -DskipTests clean package

# Runtime stage: lightweight JRE image
FROM eclipse-temurin:11-jre
WORKDIR /app

# Copy built jar from the build stage (assumes a single jar in target)
COPY --from=build /workspace/app/target/*.jar app.jar

# Expose the port the gateway listens on
EXPOSE 8080

# Default JVM options (can be overridden at runtime)
ENV JAVA_OPTS="-Xms128m -Xmx512m"

# Simple healthcheck: request gateway root or actuator health if available
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget -qS --spider http://localhost:8080/ || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

