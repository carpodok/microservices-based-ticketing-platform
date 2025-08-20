# Microservices-based Ticketing Platform

This project demonstrates a ticketing system built with Spring Boot microservices and gRPC. Each service runs independently and communicates through REST or gRPC calls.

## Architecture & Workflow
- **Gateway service** routes `/api/catalog/**`, `/api/inventory/**`, and `/api/booking/**` to the respective services.
- **Auth service** registers users and issues JWT tokens for authentication.
- **Catalog service** stores events, venues, halls, and price tiers.
- **Inventory service** manages event seats and supports listing, locking, releasing, and selling seats.
- **Booking service** orchestrates bookings and calls the inventory service over gRPC to lock or finalize seats.
- **Shared library** provides protobuf definitions for seat locking and booking summaries.

### Responsibility split

**Gateway**
- Authenticate requests (validate JWTs, sessions, or API keys).
- Perform coarse authorization (e.g., block anonymous users from `/api/**`).
- Relay the caller's token to downstream services.
- Apply simple rate limiting and request size limits.
- Terminate TLS and handle CORS for browser clients.
- Add observability data such as request IDs and metrics.
- Provide basic resilience via timeouts and retries while keeping routing thin.

**Microservices**
- Re-validate JWTs received from the gateway using the same signing keys.
- Enforce fine-grained authorization rules (e.g., "only owners can update bookings").
- Validate input and encode output safely.
- Authenticate when calling other services (mTLS or service credentials).

Typical flow:
1. Client calls the gateway to retrieve catalog data.
2. Booking service starts a booking and asks inventory to lock seats.
3. Payment confirmation leads to selling seats; cancellation releases the locks.

## Running locally
1. **Prerequisites**: JDK 21, Maven, Docker.
2. **Clone**: `git clone <repo-url>`
3. **Build shared library**:
   ```bash
   cd ticketing-shared-lib
   ./mvnw install
   ```
4. **Start PostgreSQL** (one container for all DBs):
   ```bash
   docker run --name ticketing-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
   ```
   Create databases `catalog`, `inventory`, and `booking` inside this instance.
5. **Run services** (each in its own terminal):
   ```bash
   cd ticketing-catalog-service && ./mvnw spring-boot:run
   cd ticketing-inventory-service && ./mvnw spring-boot:run
   cd ticketing-booking-service && ./mvnw spring-boot:run
   cd ticketing-auth-service && ./mvnw spring-boot:run
   cd ticketing-gateway-service && ./mvnw spring-boot:run
   ```
6. **Access via gateway**: `http://localhost:8080/api/...`

