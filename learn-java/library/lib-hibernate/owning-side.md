#### Identifying the Owning Side in JPA Relationships
https://medium.com/@sridhar.jammalamadaka/one-to-many-mapping-in-spring-boot-jpa-with-detailed-break-down-9c76459d768a

* Owning Side: The owning side of the relationship is the entity that contains the foreign key.
* Non-Owning (Inverse) Side:

* Owning Side: 
In a bidirectional @OneToMany relationship, the "Many" side is typically [the owning side]. 
This means the entity on the "many" side (e.g., Employee in the Department-Employee example) 
will have a reference to the "one" side (e.g., Department) 
and its corresponding [foreign key column] in its database table. 

The [owning side] is responsible for [persisting, updating, and deleting the relationship].

* Inverse Side (Non-Owning Side): 
The "One" side (e.g., Department) in a bidirectional @OneToMany relationship is the inverse or non-owning side. 
It will typically use the mappedBy attribute in its @OneToMany annotation to indicate 
that the relationship is managed by the "many" side.

The inverse side does not manage the foreign key directly.

https://stackoverflow.com/questions/2749689/what-is-the-owning-side-in-an-orm-mapping
https://medium.com/@hk09/spring-boot-one-to-many-relationship-e617183b7861
https://www.canosielabs.com/blog/jpa-with-hibernate-relationship-mapping-using-annotations/

https://docs.hibernate.org/orm/7.1/userguide/html_single/#associations-one-to-many-bidirectional

#### Unidirectional @OneToMany
https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/