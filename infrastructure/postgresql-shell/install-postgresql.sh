#!/bin/bash

sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -

sudo apt-get update
sudo apt-get -y install postgresql-14
sudo apt-get -y install mc

sudo cp /vagrant/postgresql.conf /etc/postgresql/14/main/postgresql.conf
sudo cp /vagrant/pg_hba.conf /etc/postgresql/14/main/pg_hba.conf

sudo su postgres -c "psql -c \"CREATE ROLE vagrant SUPERUSER LOGIN PASSWORD 'vagrant'\" "

sudo systemctl restart postgresql