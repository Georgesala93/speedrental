# ---- Build stage: compile with Maven (JDK 11) ----
FROM maven:3.8.6-jdk-11 AS build
WORKDIR /workspace/app

# Copy pom first to leverage Docker layer caching for dependencies
COPY pom.xml ./

# Download dependencies to have a cached layer (faster incremental builds)
RUN mvn -B -DskipTests dependency:go-offline

# Copy sources and build the application
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- Runtime stage: lightweight JRE ----
FROM eclipse-temurin:11-jre

# Create a non-root user for increased security
RUN useradd --create-home appuser || true
WORKDIR /app

# Copy the fat jar produced in the build stage
COPY --from=build /workspace/app/target/*.jar app.jar

# Expose the port configured in application.yml
EXPOSE 8081

# Default JVM options (override at runtime if needed)
ENV JAVA_OPTS="-Xms128m -Xmx512m"

# Simple healthcheck using curl/wget (wget is available in this image)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget -qS --spider http://localhost:8081/ || exit 1

# Run as non-root user
USER appuser

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

