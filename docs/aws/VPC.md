A [security group] controls the traffic that is allowed to reach and leave the resources that it is associated with

When you create a VPC, it comes with a [default security group]

You can create additional security groups for a VPC, each with their own [inbound and outbound rules]. 
You can specify the source, port range, and protocol for each [inbound rule]. 
You can specify the destination, port range, and protocol for each [outbound rule].

The [security group] acts as a virtual [firewall].

VPC - Virtual Private Cloud
* subnets 
* * Public subnets
* * private subnets

* * public IPv4
* * Elastic IP
* * private (internal) IP address

* gateway
* * an internet gateway
* * AWS Transit Gateway
* * NAT gateway (private / public connection)

* security groups

* route tables

* NACL Network Access Control List 

#### Question 
Route 53 ?