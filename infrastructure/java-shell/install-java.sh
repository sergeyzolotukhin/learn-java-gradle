#!/bin/bash

echo 'Update index of packages'
sudo apt-get update

echo 'Install tools'
sudo apt-get -y install mc


echo 'Install the required dependencies ==============================================================================='
sudo apt-get -y install gnupg2 software-properties-common

echo 'add the Java repository ========================================================================================='
sudo add-apt-repository ppa:linuxuprising/java -y

echo 'Update index of packages ========================================================================================'
sudo apt-get update

echo 'Install Java ===================================================================================================='
#sudo apt-get -y install oracle-java17-installer

echo 'set this Java version as the default============================================================================='
#sudo apt-get -y oracle-java17-set-default