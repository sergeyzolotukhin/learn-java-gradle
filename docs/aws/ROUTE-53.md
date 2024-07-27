DNS works on 53 port

dns record type
---
ns - name server
a - address
cname - canonical name -- ???

alias record - ??? 
SOA records - ?

routing policies
---
Simple routing policy 
    One recoded with multiple IP. Return all values in random order  
Weighted routing policy – 
    Use to route traffic to multiple resources in proportions that you specify.
    We can use health check for records
    You can set up SNS notification when health check is fails

Failover routing policy 
    Use when you want to configure active-passive failover.

Geolocation routing policy 
    Use when you want to route traffic based on the location of your users.
Geoproximity routing policy  
    Use when you want to route traffic based on the location of your resources and, 
    optionally, shift traffic from resources in one location to resources in another location.
    traffic flow ??? bias ???

Latency routing policy 
    Use when you have resources in multiple AWS Regions 
    and you want to route traffic to the Region that provides the best latency. 

IP-based routing policy – Use when you want to route traffic based on the location of your users, and have the IP addresses that the traffic originates from.
Multivalue answer routing policy – Use when you want Route 53 to respond to DNS queries with up to eight healthy records selected at random. You can use multivalue answer routing to create records in a private hosted zone.
