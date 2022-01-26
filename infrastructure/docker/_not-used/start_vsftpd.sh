#!/bin/sh

#Remove all ftp users
#grep '/ftp/' /etc/passwd | cut -d':' -f1 | xargs -n1 deluser

#if [ -z "$USERS" ]; then
#  USERS="ftp|alpineftp"
#fi
#
#for i in $USERS ; do
#    NAME=$(echo $i | cut -d'|' -f1)
#    PASS=$(echo $i | cut -d'|' -f2)
#  FOLDER=$(echo $i | cut -d'|' -f3)
#     UID=$(echo $i | cut -d'|' -f4)
#
#  if [ -z "$FOLDER" ]; then
#    FOLDER="/ftp/$NAME"
#  fi
#
#  if [ ! -z "$UID" ]; then
#    UID_OPT="-u $UID"
#  fi
#
#  echo -e "$PASS\n$PASS" | adduser -h $FOLDER -s /sbin/nologin $UID_OPT $NAME
#  mkdir -p $FOLDER
#  chown $NAME:$NAME $FOLDER
#  unset NAME PASS FOLDER UID
#done


#if [ -z "$MIN_PORT" ]; then
#  MIN_PORT=21000
#fi
#
#if [ -z "$MAX_PORT" ]; then
#  MAX_PORT=21010
#fi
#
#if [ ! -z "$ADDRESS" ]; then
#  ADDR_OPT="-opasv_address=$ADDRESS"
#fi

# Used to run custom commands inside container
#if [ ! -z "$1" ]; then
#  exec "$@"
#else
#  exec /usr/sbin/vsftpd -opasv_min_port=$MIN_PORT -opasv_max_port=$MAX_PORT $ADDR_OPT /etc/vsftpd/vsftpd.conf
#fi

echo Hello Serhij Zolotukhin