Ansible-Vagrant-ELK
=====================

This is an Ansible playbook to setup ELK environment for **private** production and test environments

Warning: roles does not contains any security features

Environment Basic
-----------------
1. Download and install Ansible

   ``` bash
      pip install ansible
   ```

Test Environment
----------------
1. Run Vagrant

   ``` bash
      vagrant up
      # Note:
      # Vagrant will set up new VM for you and apply to it `elk.yml`, that will
      # set up ELK into VM.
      # If something goes wrong you can reapply `elk.yml` with command:
      # $ vagrant provision
   ```

2. Open [Kibana](http://192.168.33.16:5601/app/kibana) in browser

Deploy to Production
--------------------
1. Install ELK

   ``` bash
      ansible-playbook -i prod_hosts elk.yml
   ```

3. Install Filebeat

   ``` bash
      $ ansible-playbook -i prod_hosts filebeat.yml
   ```

Docs
----
1) [how-to-install-elasticsearch-logstash-and-kibana-elk-stack-on-ubuntu-14-04](https://www.digitalocean.com/community/tutorials/how-to-install-elasticsearch-logstash-and-kibana-elk-stack-on-ubuntu-14-04)
2) [elastic.co/products](https://www.elastic.co/products)
3) [kibana-server-properties](https://www.elastic.co/guide/en/kibana/current/kibana-server-properties.html)
4) [configuration-filebeat-option](https://www.elastic.co/guide/en/beats/filebeat/current/configuration-filebeat-options.html)
