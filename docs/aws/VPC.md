VPC - Virtual Private Cloud
---
* gateway
* * an internet gateway
* * Transit Gateway
* * NAT gateway (private / public connection)

subnets
---
* * Public subnets
* * private subnets
One subnet can be at one AZ

* * public IPv4 - public dynamic IP (changed when restart)
* * Elastic IP  - public dynamic IP (limit 5 per account)
* * private (internal) IP address - private IP (dynamic ??? )

route tables
---

Security groups and NACLs act as virtual firewalls.
* security groups - A security group acts as a virtual [firewall for EC2 instances] to control the inbound and outbound traffic based on some defined rules. 
    It ensures [instance-level security].
* NACL Network Access Control List - NACL stands for Network Access Control List, 
  and it [controls the traffic to or from a subnet] according to some defined rules

security group
---
A [security group] controls the traffic that is allowed to reach and leave the resources that it is associated with

When you create a VPC, it comes with a [default security group]

You can create additional security groups for a VPC, each with their own [inbound and outbound rules].
You can specify the source, port range, and protocol for each [inbound rule].
You can specify the destination, port range, and protocol for each [outbound rule].

The [security group] acts as a virtual [firewall].
by default, all inbound is disabled
by default, all outbound is allowed
take effect immediately
security group is stateful !!!

security group <--many-to-many--> EC2 instance

NACL - Network Access Control list
---
NACL is stateless !!!
Default NACL allow all
Custom NACL denies all
Each subnet should have NACL , only one is allowed
We can block IP addresses via NACL - not a security groups. 
Rules checked in ordered way, start from lowest
Ephemeral ports !!!

Endpoints
---
???

Route 53 - DNS
---
record type
    A
    C
    NS

#### Availability Zones

https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-availability-zones