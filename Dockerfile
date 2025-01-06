FROM openjdk:17-jdk-slim
LABEL authors="ameni"
COPY target/internship-project-1.0.0.jar internship-project-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/internship-project-1.0.0.jar"]



