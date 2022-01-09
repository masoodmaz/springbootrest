FROM openjdk:18-jdk-alpine3.15
ADD target/broker-0.0.1-SNAPSHOT.jar broker-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx512m","-jar","/broker-0.0.1.jar"]
