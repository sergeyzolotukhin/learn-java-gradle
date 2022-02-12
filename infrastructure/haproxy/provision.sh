#!/bin/bash

echo "Installing Jenkins and Java"
sudo apt-get update > /dev/null 2>&1
sudo apt-get -y install mc > /dev/null 2>&1

#echo "Port 22" > /etc/ssh/sshd_config
echo "Port 3334" > /etc/ssh/sshd_config

sudo systemctl restart ssh

echo "Success"