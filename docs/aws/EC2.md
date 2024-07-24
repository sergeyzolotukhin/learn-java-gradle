Launch Configuration Template
Auto Scale Group

Elastic Load Balancers
* Application
* Network

Target Groups

Resources groups
Tagging

Placement group
---
* Cluster – Packs instances close together inside an Availability Zone
* Partition – Spreads your instances across logical partitions 
such that groups of instances in one partition 
do not share the underlying hardware with groups of instances in different partitions.
* Spread – Strictly places a small group of instances across distinct underlying hardware to reduce correlated failures.

Placement groups are optional. 
If you don't launch your instances into a placement group, 
EC2 tries to place the instances in such a way 
that all of your instances are spread out across the underlying hardware to minimize correlated failures. 

Bootstrap script - run when first start instance under root

Network options (virtual network cards)
----
ENI -  Elastic Network Interfaces - for day=to-day
Enhancement Networking - SE-IOV - high performance - 10 Gb - 100 Gb
EFA -  Elastic Fabric Adapter - high performance for machine learning
VF - virtual function interface (old)

ENA vs VF

Other
---
https://hkcodeblogs.medium.com/aws-ec2-create-and-connect-to-instance-via-ssh-354a0c1909f
https://www.clickittech.com/aws/connect-ec2-instance-using-ssh/
