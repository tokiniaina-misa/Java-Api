FROM openjdk:17-slim

WORKDIR /app

COPY src/ /app/src/
COPY front/ /app/front/

RUN javac src/*.java

EXPOSE 8080

CMD ["java", "-cp", "src", "Main"]
