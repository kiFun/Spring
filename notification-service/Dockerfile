FROM eclipse-temurin:25
VOLUME /tmp
COPY target/notification-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
