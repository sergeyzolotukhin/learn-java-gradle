FROM alpine:3.19.1
RUN apk add --no-cache git
RUN apk add --no-cache openssh
WORKDIR /data
RUN git clone https://github.com/sergeyzolotukhin/learn-java-gradle.git /data/app
WORKDIR /data/app
CMD ["ls", "-l",  "."]