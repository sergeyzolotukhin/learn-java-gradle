FROM amazoncorretto:20
RUN mkdir /app
COPY ./build/libs /app
WORKDIR /app

CMD ["java", "-jar", "application.jar"]