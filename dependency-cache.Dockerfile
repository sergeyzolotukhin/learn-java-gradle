FROM gradle:8.6.0-jdk21 as builder
#RUN mkdir /src
COPY . /src
WORKDIR /src
RUN gradle build -x test --no-daemon

FROM gradle:8.6.0-jdk21
COPY --from=builder /root/.gradle /root/.gradle