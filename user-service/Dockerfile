FROM eclipse-temurin:25
VOLUME /tmp
COPY target/user-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
