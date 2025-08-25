# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Build shared protobuf library
COPY ticketing-shared-lib ./ticketing-shared-lib
RUN cd ticketing-shared-lib && ./mvnw install -DskipTests

# Build gateway service
COPY ticketing-gateway-service ./ticketing-gateway-service
RUN cd ticketing-gateway-service && ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
COPY --from=build /workspace/ticketing-gateway-service/target/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
