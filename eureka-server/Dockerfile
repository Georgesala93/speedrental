# Etapa de compilación: usa Maven con JDK 11 para construir el jar
FROM maven:3.8.6-jdk-11 AS build
WORKDIR /workspace/app

# Copiar archivos de Maven y fuentes
COPY pom.xml ./
COPY src ./src

# Construir el artefacto sin tests para velocidad; en CI puedes quitar -DskipTests
RUN mvn -B -DskipTests clean package

# Etapa de ejecución: imagen ligera con JRE
FROM eclipse-temurin:11-jre
WORKDIR /app

# Copiar jar compilado desde la etapa de build
COPY --from=build /workspace/app/target/*.jar app.jar

# Exponer puerto de Eureka
EXPOSE 8761

# Usar variables de entorno por defecto que pueden sobrescribirse desde docker-compose
ENV JAVA_OPTS="-Xms128m -Xmx512m"

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget -qS --spider http://localhost:8761/ || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

