FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy full project
COPY . .

# Install Maven
RUN apk add --no-cache maven

# Build jar file
RUN mvn clean package -DskipTests

# Run jar
CMD ["java", "-jar", "target/hierarchy-0.0.1-SNAPSHOT.jar"]
