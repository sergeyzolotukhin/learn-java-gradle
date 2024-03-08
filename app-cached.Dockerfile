FROM dependency-cache:latest
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN gradle build -x test --profile
CMD ["ls", "-l",  "."]