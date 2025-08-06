FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=build/libs/bid-panda.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]