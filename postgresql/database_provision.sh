sudo apt-get clean
sudo apt-get update

sudo apt-get remove postgresql postgresql-contrib
sudo apt-get remove mc

sudo apt-get -y install postgresql postgresql-contrib
sudo apt-get -y install mc

#echo "-------------------- creating postgres vagrant role with password vagrant"
# Create Role and login
#sudo su postgres -c "psql -c \"CREATE ROLE vagrant SUPERUSER LOGIN PASSWORD 'vagrant'\" "

#echo "-------------------- creating wtm database"
# Create WTM database
#sudo su postgres -c "createdb -E UTF8 -T template0 --locale=en_US.utf8 -O vagrant wtm"
