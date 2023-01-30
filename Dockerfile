FROM openjdk:17.0.5
ADD ./target/bookapplication.jar bookapplication.jar
ENTRYPOINT ["java", "-jar", "bookapplication.jar"]
EXPOSE 8080