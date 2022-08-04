#!/bin/bash

echo 'Add Amazon Repository ==========================================================================================='
wget -O- https://apt.corretto.aws/corretto.key | sudo apt-key add -
sudo add-apt-repository 'deb https://apt.corretto.aws stable main'

echo 'Update index of packages ========================================================================================'
sudo apt-get update

echo 'Install tools ==================================================================================================='
sudo apt-get -y install mc

echo 'Install Java ===================================================================================================='
sudo apt-get install -y java-1.8.0-amazon-corretto-jdk

echo 'Verify Java JDK Installation====================================================================================='
sudo java -version
