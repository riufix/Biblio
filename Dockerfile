# --- Build ---
FROM maven:3.9-eclipse-temurin-21 as build

COPY pom.xml .

copy src ./src
RUN mvn clean package -DskipTests

# --- Run ---
FROM eclipse-temurin:21

COPY --from=build /target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]