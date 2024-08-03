FROM dependency-cache:latest
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN gradle build -x test --profile
CMD ["ls", "-l",  "."]

# https://maven.apache.org/resolver/local-repository.html#use-cases