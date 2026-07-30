# compilo y traigo todas las dependencia de maven
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app/backend
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests

# aca solo traigo la parte de ejecucion osea hago una segunda imagen 
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app/backend/ejecucion
COPY --from=build /app/backend/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
