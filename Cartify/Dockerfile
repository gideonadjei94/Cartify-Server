FROM openjdk:17-jdk
WORKDIR /app
COPY target/Cartify-0.0.1-SNAPSHOT.jar /app/cartify.jar
EXPOSE 8000
CMD ["java", "-jar", "cartify.jar"]