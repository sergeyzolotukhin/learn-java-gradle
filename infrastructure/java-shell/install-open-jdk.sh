#!/bin/bash

echo 'Update index of packages'
sudo apt-get update

echo 'Install tools'
sudo apt-get -y install mc


echo 'Install Java ===================================================================================================='
sudo apt -y install openjdk-18-jdk

echo 'Verify Java JDK Installation====================================================================================='
sudo java --version