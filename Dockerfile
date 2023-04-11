FROM openjdk:8u212-jre-alpine
EXPOSE 8080
COPY ./target/java-app-*.jar /usr/app/
WORKDIR /usr/app
CMD java -jar java-app-*.jar
