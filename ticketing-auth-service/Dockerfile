# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Build shared protobuf library
COPY ticketing-shared-lib ./ticketing-shared-lib
RUN cd ticketing-shared-lib && chmod +x mvnw && ./mvnw install -DskipTests

# Build auth service
COPY ticketing-auth-service ./ticketing-auth-service
RUN cd ticketing-auth-service && chmod +x mvnw && ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
COPY --from=build /workspace/ticketing-auth-service/target/*.jar /app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]
