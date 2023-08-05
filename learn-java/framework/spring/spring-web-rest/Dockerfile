FROM amazoncorretto:20
RUN mkdir /app
COPY ./build/libs /app
WORKDIR /app

#RUN ls -l .
#RUN java -version
CMD ["java", "-jar", "application.jar"]
#CMD ["ls",  "-l", "."]
#CMD ["pwd"]