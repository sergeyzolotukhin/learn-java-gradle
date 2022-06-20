#!/bin/bash

echo 'Add jfrog repository'
sudo apt-get update
wget -qO - https://api.bintray.com/orgs/jfrog/keys/gpg/public.key | sudo apt-key add -
echo "deb https://jfrog.bintray.com/artifactory-debs focal main" | sudo tee /etc/apt/sources.list.d/jfrog.list

echo 'Update index of packages'
sudo apt-get update

echo 'Install JFrog Artifactory'
sudo apt-cache madison jfrog-artifactory-oss
sudo apt -y install jfrog-artifactory-oss=7.27.9 -V

echo 'Install tools'
sudo apt-get -y install mc

echo 'Restart JFrog Artifactory'
sudo systemctl start artifactory.service
sudo systemctl enable artifactory.service