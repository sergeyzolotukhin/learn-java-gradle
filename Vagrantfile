# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
    config.vm.define "docker-engine-1" do |d|
        # Ubuntu 22.04.2 LTS - Jammy Jellyfish
        d.vm.box = "ubuntu/jammy64"
        d.vm.network :private_network, ip: "192.168.56.2"

        d.vm.provider "virtualbox" do |vb|
            vb.name = "docker-engine-learn-java-1"
            vb.memory = "16384"
            vb.cpus = 8
            vb.gui = true
        end

        d.vm.provision "shell", path: "provision/root-login-enable.sh"
        # d.vm.provision "file", source: "~/.ssh/id_rsa.pub", destination: "~/.ssh/id_rsa.pub"

        # Install Docker Engine
        d.vm.provision :docker
        d.vm.provision "shell", path: "provision/docker-engine.sh"

        # Install Docker Compose
        # First, install required plugin https://github.com/leighmcculloch/vagrant-docker-compose:
        # vagrant plugin install vagrant-docker-compose
        # config.vm.provision :docker_compose
    end
end