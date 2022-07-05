#!/bin/bash

echo 'Update index of packages'
sudo apt-get update

echo 'Install tools'
sudo apt-get -y install mc


echo 'Install the required dependencies ==============================================================================='
sudo apt install -y libc6-x32 libc6-i386

echo 'download Oracle Java JDK 17======================================================================================'
sudo wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb

echo 'Install Java ===================================================================================================='
sudo dpkg -i jdk-17_linux-x64_bin.deb

echo 'Verify Java JDK Installation====================================================================================='
sudo java --version