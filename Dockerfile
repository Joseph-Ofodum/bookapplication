FROM openjdk:17.0.5
ADD ./target/Book-Application-0.0.1-SNAPSHOT.jar Book-Application-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Book-Application-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080