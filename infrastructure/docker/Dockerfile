FROM ubuntu:20.04

RUN apt-get update \
    && apt-get -y install vsftpd

# support tools
#RUN apt-get -y install mc

#RUN apk --no-cache add vsftpd

COPY start_vsftpd.sh /bin/start_vsftpd.sh
#COPY vsftpd.conf /etc/vsftpd/vsftpd.conf

EXPOSE 21
# EXPOSE 21 21000-21010
#VOLUME /ftp/ftp
VOLUME /ftp

STOPSIGNAL SIGKILL

CMD ["/usr/sbin/vsftpd","-obackground=NO"]
#ENTRYPOINT ["/bin/start_vsftpd.sh"]