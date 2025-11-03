FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/wallet-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]