#!/bin/bash

echo 'Update index of packages ========================================================================================'
sudo apt-get update

echo 'Install tools ==================================================================================================='
sudo apt-get -y install mc


echo 'Install the required dependencies ==============================================================================='
sudo apt-get -y install gnupg2 software-properties-common
sudo apt-get install -y python-software-properties debconf-utils

echo 'add the Java repository ========================================================================================='
sudo add-apt-repository ppa:linuxuprising/java -y

echo 'Update index of packages ========================================================================================'
sudo apt-get update

echo 'Install Java ===================================================================================================='
echo "oracle-java17-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
echo oracle-java17-installer shared/accepted-oracle-license-v1-3 select true | sudo /usr/bin/debconf-set-selections
echo oracle-java17-installer shared/accepted-oracle-licence-v1-3 boolean true | sudo /usr/bin/debconf-set-selections
sudo apt-get -y install oracle-java17-installer

echo 'set this Java version as the default============================================================================='
sudo apt-get -y oracle-java17-set-default

echo 'Verify Java JDK Installation====================================================================================='
sudo java --version
