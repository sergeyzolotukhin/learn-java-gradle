FROM gradle:8.1.1-jdk17
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN gradle build

RUN ls -l ./library/lib-open-nlp/build/libs
CMD ["ls -l /src"]