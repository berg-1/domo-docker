FROM openjdk:8u342-slim-buster
COPY target/domo-docker-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar
