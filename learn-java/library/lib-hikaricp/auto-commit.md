#### Spring boot auto commit
https://github.com/spring-projects/spring-boot/issues/9261
https://github.com/spring-projects/spring-boot/pull/9737

#### Hibernate auto commit

The hibernate.connection.autocommit property in Hibernate controls the autocommit mode of the underlying JDBC connection.
- Purpose: 
When set to true, every individual SQL statement executed through the JDBC connection is treated 
as a separate transaction and implicitly committed. 

When false, multiple SQL statements can be grouped into a single transaction,
which must then be explicitly committed or rolled back.

- Default Behavior: 
By default, Hibernate disables autocommit on the JDBC connection (sets autocommit to false). 
This allows Hibernate to manage transaction boundaries and ensure that 
multiple operations within a unit of work are part of the same database transaction.

- Recommendation: 
In most application scenarios, especially those involving complex business logic 
and multiple database operations within a single unit of work, 
it is strongly recommended to keep hibernate.connection.autocommit set to false (or rely on Hibernate's default behavior). 
This enables proper transaction management, including the ability to commit or roll back a series of operations atomically.

- Explicit Transaction Management: 
When autocommit is disabled, applications must explicitly manage transactions using Hibernate's Transaction API 
(e.g., session.beginTransaction(), transaction.commit(), transaction.rollback()).

- Considerations for true: 
Setting hibernate.connection.autocommit to true is generally discouraged for application-level data access, 
as it can lead to inconsistent data states and make error handling more complex. 
It might be used in specific, isolated cases like ad-hoc SQL console work or certain testing scenarios, 
but it is not suitable for typical enterprise applications.

- Interaction with Connection Providers: 
If a connection pooling solution (e.g., HikariCP) is used, it often provides its own mechanism to configure the autocommit mode of connections in the pool. 
The hibernate.connection.provider_disables_autocommit property can be used to inform Hibernate 
that the connection provider already handles disabling autocommit, potentially optimizing connection acquisition.

#### HikariCP

#### JDBC auto commit
https://www.baeldung.com/java-jdbc-auto-commit

