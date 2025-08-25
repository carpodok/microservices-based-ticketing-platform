# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Build shared protobuf library
COPY ticketing-shared-lib ./ticketing-shared-lib
RUN cd ticketing-shared-lib && ./mvnw install -DskipTests

# Build booking service
COPY ticketing-booking-service ./ticketing-booking-service
RUN cd ticketing-booking-service && ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
COPY --from=build /workspace/ticketing-booking-service/target/*.jar /app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]
