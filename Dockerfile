FROM openjdk:17
ADD ./target/bookapplication.jar bookapplication.jar
ENTRYPOINT ["java", "-jar", "bookapplication.jar"]
EXPOSE 8080