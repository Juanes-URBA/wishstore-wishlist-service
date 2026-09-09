# ---------- Etapa 1: build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el codigo fuente y construimos el jar
COPY src ./src
RUN mvn clean package -DskipTests -B

# ---------- Etapa 2: runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Usuario no root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/target/wishstore-wishlist-service.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]