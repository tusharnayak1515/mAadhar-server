FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/mAadhar-0.0.1-SNAPSHOT.jar /app/mAadhar-0.0.1-SNAPSHOT.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "mAadhar-0.0.1-SNAPSHOT.jar"]