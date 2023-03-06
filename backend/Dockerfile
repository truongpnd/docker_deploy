FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw dependency:go-offline
CMD ["./mvnw", "spring-boot:run"]
EXPOSE 8080
