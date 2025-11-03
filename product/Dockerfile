FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/product-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]