#### Postgresql replication

    vargran default 
        username: vagrant
        password: vagrant
    
#### Vagrant network

    (network){https://www.oreilly.com/library/view/vagrant-up-and/9781449336103/ch04.html}
    https://docs.ansible.com/ansible/latest/modules/list_of_database_modules.html
    https://medium.com/@khandelwal12nidhi/setup-ssh-key-and-initial-user-using-ansible-playbook-61eabbb0dba4
    https://docs.ansible.com/ansible/latest/modules/authorized_key_module.html
    https://github.com/phinze/vagrant-host-shell
    https://github.com/hashicorp/vagrant/wiki/Available-Vagrant-Plugins
    
#### Ruby

    http://nashbridges.me/blocks-in-ruby
    
#### TODO
    
    Use static private key at vagrant
    How to enable login/password auth at linux
    
#### Postgresql setup

    https://blog.apcelent.com/using-ansible-to-set-up-postgresql.html

#### My steps how to learn ansible.

DONE:
    * How to install ansible with vagrant oo the one node.
    * How to enable connect to other node via password.
    * How to create simple play book to ping other node.
    * How to create a simple role.
    * How to apply separate roles to different hosts.

IN PROGRESS:
    * Enable access to Postgresql for other hosts

TODO:

    * Create database via ansible
    * Insert data via ansible
    * Create database dump
    * Restore database from dump

    * Execute custom python script on the node
    * Create custom module

##### Enable access to Postgresql for other hosts

    I had the same exact problem. The issue was on the host side, basically the firewall was blocking the port I was using. So this is what I did (I am using OSX Mavericks)

    Open the port (Host)
        sudo ipfw add 7000 allow tcp from any to any dst-port 7001

    Modify Vagrantfile in order to allow portforwarding
        config.vm.network "forwarded_port", guest: 5432, host: 7001

    Edit postgresql.conf (Guest)
        listen_addresses = '*'

    Edit pg_hba.conf (you might want to tune this better)
        host    all       all   0.0.0.0/0     md5

    Now, from the host connect normally using the port (in my case 7001) and 'localhost' as host address

#### Vagrant DNS

    https://github.com/BerlinVagrant/vagrant-dns
    https://github.com/vagrant-landrush/landrush
    https://github.com/devopsgroup-io/vagrant-hostmanager

    https://app.vagrantup.com/boxes/search
