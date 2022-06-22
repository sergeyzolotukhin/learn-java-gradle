#!/bin/bash

echo 'Update index of packages'
sudo apt-get update

echo 'Install tools'
sudo apt-get -y install mc

echo 'Copy installer archive'
sudo mkdir /opt/jfrog-artifactory
sudo cp /vagrant/jfrog-artifactory-oss-7.39.4-linux.tar.gz /opt/jfrog-artifactory
cd /opt/jfrog-artifactory || return

#echo 'Set the JFrog Home'
#export JFROG_HOME=/opt/jfrog-artifactory

echo 'Extract installer archive'
sudo tar -xf jfrog-artifactory-oss-7.39.4-linux.tar.gz
sudo mv artifactory-oss-7.39.4 artifactory

#echo 'Install service JFrog Artifactory'
#sudo /opt/jfrog-artifactory/artifactory/app/bin/installService.sh

#echo 'Restart JFrog Artifactory'
#sudo systemctl start artifactory.service
#sudo systemctl enable artifactory.service