FROM eclipse-temurin:21-jdk-jammy
COPY . /src
WORKDIR /src
RUN ./gradlew build -x test

# https://proandroiddev.com/gradle-cache-your-builds-best-friend-4970ad32420e