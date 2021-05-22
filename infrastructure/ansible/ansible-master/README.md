#### How to run the playbook?

    1. Login to the 192.168.1.40 via ssh with user name vagrant and password vagrant

    2. Execute following commands:

        cd /vagrant/ansible
        ansible-playbook playbook.yml

#### Known issue

    Failed to connect to the host via ssh:  WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!
    ssh-keygen -R 192.168.1.41