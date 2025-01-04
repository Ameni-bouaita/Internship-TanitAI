FROM ubuntu:latest
LABEL authors="ameni"
COPY target/Foyer-0.1.1.jar Foyer-0.1.1.jar
ENTRYPOINT ["java","-jar","/Foyer-0.1.1.jar"]




