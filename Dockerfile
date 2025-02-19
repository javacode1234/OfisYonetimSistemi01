# for railway start
FROM maven:3-openjdk-17 AS build
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
# for railway end

#FROM openjdk:17
#ADD target/*.jar OfisYonetimSistemiV01.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "OfisYonetimSistemiV01.jar"]