FROM ubuntu:20.04

RUN apt-get update \
    && apt-get -y install vsftpd

# network / others tools
RUN apt-get -y install iproute2

# add users / groups
RUN useradd -d /ftp_site/TSOC_HOUSE -s /bin/bash tsoc-house-ftp-upload \
  && echo "tsoc-house-ftp-upload:tsoc-house-ftp-upload" | chpasswd \
  && groupadd tsoc-house-ftp-uploader \
  && usermod -G tsoc-house-ftp-uploader tsoc-house-ftp-upload

# create dirictories
RUN mkdir -p /ftp_site/TSOC_HOUSE/external_documents_for_etp_publication \
  && mkdir -p /ftp_site/TSOC_HOUSE/external_documents_from_etp \
  && mkdir -p /ftp_site/TSOC_HOUSE/from-tsoc-house \
  && mkdir -p /ftp_site/TSOC_HOUSE/from-ems-agc \
  && mkdir -p /ftp_site/TSOC_HOUSE/from-ems-meas \
  && mkdir -p /ftp_site/TSOC_HOUSE/to-cera \
  && mkdir -p /ftp_site/TSOC_HOUSE/to-ems-agc \
  && mkdir -p /ftp_site/TSOC_HOUSE/to-ems-beo \
  && mkdir -p /ftp_site/TSOC_HOUSE/to-ems-fz \
  && mkdir -p /ftp_site/TSOC_HOUSE/to-tsoc-house

# set owner / permissions
RUN chown -R tsoc-house-ftp-upload /ftp_site/TSOC_HOUSE \
  && chmod 775 /ftp_site/TSOC_HOUSE

RUN mkdir -p /var/run/vsftpd/empty \
  && touch /var/log/vsftpd.log

COPY vsftpd.conf /etc/vsftpd.conf

EXPOSE 21 21000-21010

STOPSIGNAL SIGKILL

CMD ["/usr/sbin/vsftpd","-obackground=NO"]
