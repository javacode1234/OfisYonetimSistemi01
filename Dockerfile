FROM maven:3.3.9-jdk-8-alpine AS build
WORKDIR /app
COPY . .
#RUN mvn clean install -DskipTests
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar OfisYonetimSistemiV01.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "OfisYonetimSistemiV01.jar"]