A [security group] controls the traffic that is allowed to reach and leave the resources that it is associated with

When you create a VPC, it comes with a [default security group]

You can create additional security groups for a VPC, each with their own [inbound and outbound rules]. 
You can specify the source, port range, and protocol for each [inbound rule]. 
You can specify the destination, port range, and protocol for each [outbound rule].

The [security group] acts as a virtual [firewall].

VPC - Virtual Private Cloud
* gateway
* * an internet gateway
* * Transit Gateway
* * NAT gateway (private / public connection)


* subnets
* * Public subnets
* * private subnets

* * public IPv4
* * Elastic IP
* * private (internal) IP address

* route tables

Security groups and NACLs act as virtual firewalls.
* security groups - A security group acts as a virtual [firewall for EC2 instances] to control the inbound and outbound traffic based on some defined rules. 
    It ensures [instance-level security].
* NACL Network Access Control List - NACL stands for Network Access Control List, 
  and it [controls the traffic to or from a subnet] according to some defined rules

#### Question 
Route 53 ?