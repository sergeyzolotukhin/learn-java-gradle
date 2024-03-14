FROM ubuntu:18.04

RUN mkdir -p /scripts
COPY log.sh /scripts
WORKDIR /scripts
RUN chmod +x log.sh
#RUN ls -l
RUN ./log.sh

CMD ["ls", "-l",  "."]