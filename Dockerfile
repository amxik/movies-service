FROM openjdk:8-jdk-alpine
WORKDIR /
COPY ./build/libs/movies-service-0.0.1-SNAPSHOT.jar ./movies-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","movies-service-0.0.1-SNAPSHOT.jar"]


 #ДОБАВИТЬ "-Dspring.profiles.active=production",