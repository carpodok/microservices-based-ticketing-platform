# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Build shared protobuf library
COPY ticketing-shared-lib ./ticketing-shared-lib
RUN cd ticketing-shared-lib && chmod +x mvnw && ./mvnw install -DskipTests

# Build inventory service
COPY ticketing-inventory-service ./ticketing-inventory-service
RUN cd ticketing-inventory-service && chmod +x mvnw && ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
COPY --from=build /workspace/ticketing-inventory-service/target/*.jar /app.jar
EXPOSE 8082 9090
ENTRYPOINT ["java","-jar","/app.jar"]
