#### microservice architecture patterns

- API Gateway Pattern
- Backends for Frontends (BFF) Pattern
- Service Discovery (Service Registry)
- Circuit Breaker Pattern
- Bulkhead Pattern
- Retry Pattern
- Saga Pattern (Distributed Transactions)
- Event Sourcing Pattern
- CQRS (Command Query Responsibility Segregation)
- Database per Service
- Data Sharding
- Polyglot Persistence
- Sidecar Pattern
- Smart Endpoints, Dumb Pipes
- Asynchronous Messaging (Event-Driven Architecture)
- Consumer-Driven Contracts (CDC)
- Strangler Fig Pattern
- [Shadow Deployment](./deployment/shadow-deployment.md)
- Stateless Services

https://www.geeksforgeeks.org/system-design/microservices-design-patterns/
https://microservices.io/patterns/microservices.html
https://www.designgurus.io/blog/19-essential-microservices-patterns-for-system-design-interviews?gad_source=1&gad_campaignid=23168317168&gbraid=0AAAAADME9yolt79iRgnTod4pHbGHx-Twp&gclid=CjwKCAiA3L_JBhAlEiwAlcWO57c4P2KlTCdYE4sTqw_vw3QO8d4ii0lsvDLsZjVT54QiYKZbDjjQWhoCpygQAvD_BwE
https://microservices.io/patterns/
https://dzone.com/articles/design-patterns-for-microservices
https://learn.microsoft.com/en-us/azure/architecture/microservices/design/patterns
https://medium.com/@alxkm/microservices-architecture-patterns-exploring-the-essential-27318b72c88f
https://test.tplus.market/blog/17

#### software resiliency patterns

The fundamental goal of software resiliency patterns is to build systems
that anticipate and tolerate failures while continuing to provide an acceptable level of service.

resiliency - устойчивость
anticipate - предвидеть
tolerate - терпеть, выносить

#### Bulkhead pattern

you divide into discrete sections your application's resources 
so that failure of any one of your dependencies is not cascaded into the entire system

The bulkhead pattern is a design strategy 
that isolates components of a system into separate resource pools 
to prevent a failure in one part 
from cascading and affecting the entire application.

https://medium.com/@sivaramansankar2019/bulkhead-pattern-in-microservices-spring-boot-example-d82db2c0cc5

promotion - повышение, продвижение
resilience4J 

#### Event Sourcing Pattern

Event Sourcing Pattern stores all changes to application state as a sequence of events 
rather than only saving the current state. 

####  consumer-driven contract (CDC)

A consumer-driven contract (CDC) in microservices is a testing method 
where consumers define the expectations they have of a service provider's API, 
and providers use these consumer expectations to verify that they won't break compatibility.
