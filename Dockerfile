FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-crud.jar spring-boot-crud.jar
ENTRYPOINT ["java","-jar","/spring-boot-crud.jar"]