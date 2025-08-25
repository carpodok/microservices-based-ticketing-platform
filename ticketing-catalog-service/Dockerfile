# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Build shared protobuf library
COPY ticketing-shared-lib ./ticketing-shared-lib
RUN cd ticketing-shared-lib && chmod +x mvnw && ./mvnw install -DskipTests

# Build catalog service
COPY ticketing-catalog-service ./ticketing-catalog-service
RUN cd ticketing-catalog-service && chmod +x mvnw && ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
COPY --from=build /workspace/ticketing-catalog-service/target/*.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
