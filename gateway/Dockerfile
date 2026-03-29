FROM eclipse-temurin:25
VOLUME /tmp
COPY target/gateway-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
