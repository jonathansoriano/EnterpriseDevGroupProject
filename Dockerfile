# ============================================================
# Stage 1: Build
# ============================================================
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Add metadata labels
LABEL maintainer="dharminpatel,jonathansoriano,matthewbrown,iankellenberger" \
    version="0.0.1-SNAPSHOT" \
    description="EnterpriseDevGroupProject Spring Boot Application"

# Copy the Maven wrapper and pom.xml first to leverage Docker layer caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Download dependencies (cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -q || true

# Copy source and build the application JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests -q

# ============================================================
# Stage 2: Runtime
# ============================================================
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Create a non-root group and user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copy only the compiled JAR from the build stage
COPY --from=build --chown=appuser:appgroup /app/target/*.jar app.jar

# Switch to the non-root user
USER appuser

# Expose the default Spring Boot port
EXPOSE 8080

# Health check: ping the app every 30s; fail after 3 consecutive failures
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
    CMD wget -qO- http://localhost:8080/ || exit 1

# Allow JVM tuning via JAVA_OPTS at runtime (e.g., -e JAVA_OPTS="-Xmx512m")
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
