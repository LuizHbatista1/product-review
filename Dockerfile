FROM openjdk:22-jdk


COPY target/rating-product-0.0.1-SNAPSHOT.jar /app/app.jar
COPY .env /app/.env

CMD ["java", "-jar", "/app/app.jar"]