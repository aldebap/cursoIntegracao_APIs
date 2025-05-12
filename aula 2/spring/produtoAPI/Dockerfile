# syntax=docker/dockerfile:1

#	create application image
FROM openjdk:17-jdk-alpine

WORKDIR /produtoAPI

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} produtoAPI.jar

ENTRYPOINT ["java","-jar","/produtoAPI/produtoAPI.jar"]

EXPOSE 8000

LABEL org.opencontainers.image.source https://github.com/aldebap/cursoIntegracao_APIs
