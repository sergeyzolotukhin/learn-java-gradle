FROM gradle:8.6.0-jdk21
#USER gradle
ENV GRADLE_USER_HOME=/root
COPY . /src
WORKDIR /src
RUN gradle build -x test --no-daemon --build-cache

# https://proandroiddev.com/gradle-cache-your-builds-best-friend-4970ad32420e