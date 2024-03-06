echo '================================================================================================================'
echo 'Enable remote access to docker'
echo '================================================================================================================'
sudo cp /vagrant/provision/lib/systemd/system/docker.service /lib/systemd/system/docker.service

echo 'Restart docker'
sudo systemctl daemon-reload
sudo systemctl restart docker
sudo systemctl status docker

echo '================================================================================================================'
echo 'Docker engine has installed'
echo '================================================================================================================'
