#### microservice architecture patterns

https://www.geeksforgeeks.org/system-design/microservices-design-patterns/
https://microservices.io/patterns/microservices.html

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
