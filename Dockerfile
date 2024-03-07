FROM gradle:8.6.0-jdk21
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN gradle build -x test --profile
#RUN gradle build -x test --parallel

#RUN ls -l ./library/lib-open-nlp/build/libs
CMD ["ls", "-l",  "."]