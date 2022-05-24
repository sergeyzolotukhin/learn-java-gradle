#!/bin/bash

echo 'Add postgresql repository'
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -

echo 'Update index of packages'
sudo apt-get -qq update

echo 'Install postgresql'
sudo apt-get -qq -y install postgresql-14 > /dev/null

echo 'Install tools'
sudo apt-get -qq -y install mc > /dev/null

echo 'Update postgresql configurations'
sudo cp /vagrant/postgresql.conf /etc/postgresql/14/main/postgresql.conf
sudo cp /vagrant/pg_hba.conf /etc/postgresql/14/main/pg_hba.conf

echo 'Alter postgres password'
sudo su postgres -c "psql -a -f /vagrant/alter-postgres-password.sql"

echo 'Restart postgresql'
sudo systemctl restart postgresql