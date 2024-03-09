FROM gradle:8.6.0-jdk21

WORKDIR /data
RUN git clone https://github.com/sergeyzolotukhin/learn-java-gradle.git /data/app
WORKDIR /data/app

CMD ["sh", "-c", "tail -f /dev/null"]

# https://proandroiddev.com/gradle-cache-your-builds-best-friend-4970ad32420e