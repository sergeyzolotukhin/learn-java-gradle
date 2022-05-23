#!/bin/bash

sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -

sudo apt-get update
sudo apt-get -y install postgresql

sudo sed -i "s/#listen_address.*/listen_addresses '*'/" /etc/postgresql/14/main/postgresql.conf

sudo cat >> /etc/postgresql/14/main/pg_hba.conf <<EOF
  # Accept all IPv4 connections - FOR DEVELOPMENT ONLY!!!
  host    all         all         0.0.0.0/0             md5
EOF

sudo su postgres -c "psql -c \"CREATE ROLE vagrant SUPERUSER LOGIN PASSWORD 'vagrant'\" "

sudo systemctl restart postgresql