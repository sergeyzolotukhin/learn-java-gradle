Elastic Load Balancing -is same as HTTP / HA load balancing (network) 

4 type:
* Application - HTTP / HTTPS (7 layer of OSI) 
* Network - (4 layer of OSI) - high performance (tcp/udp)
* Gateway - (3 layer of OSI) - ? (ip)
* Classic - (7 layer of OSI) similar with Application LB (Test/Dev) 

Application Load Balancing
---

Listeners - protocol + port -> list rules
Rule - priority + condition -> actions
Target group - list of EC2 (health check)

Limitation - only for HTTP, HTTPS

Network Load Balancing
---

Classic Load Balancing
---

layer 7 or 4
X-Forwarded-For header http - client IP address (source)
sticky session
gateway timeout return 504
deregistration delay