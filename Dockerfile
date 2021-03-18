FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/challenge-dev-1.0.0-SNAPSHOT.jar challenge-dev.jar
ENTRYPOINT ["java","-jar","/challenge-dev.jar"]