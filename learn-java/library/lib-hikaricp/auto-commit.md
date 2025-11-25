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
If a connection pooling solution (e.g., HikariCP) is used, 
it often provides its own mechanism to configure the autocommit mode of connections in the pool. 
The hibernate.connection.provider_disables_autocommit property can be used to inform Hibernate 
that the connection provider already handles disabling autocommit, potentially optimizing connection acquisition.

#### HikariCP

The default value for HikariCP's autoCommit is true. 
This means that by default, each SQL statement executed on a connection from the pool is automatically committed unless explicitly disabled. 
It is recommended to set spring.datasource.hikari.auto-commit=false
and manage transactions explicitly for better control and performance, 
especially when using frameworks like Spring Data JPA with@Transactional`.

Default behavior

autoCommit is true by default.
This follows the default behavior of the underlying JDBC Connection.
With auto-commit enabled, each statement is committed as a separate transaction.

Why and when to disable auto-commit

Explicit transaction management: 
For operations that need to be treated as a single, atomic unit (e.g., updating multiple tables), 
you need to disable auto-commit to manually manage commit() and rollback() calls.

Performance: 
Disabling auto-commit can offer performance benefits, especially in applications with heavy transaction loads, 
by reducing overhead from frequent commits.

Framework integration: 
When using frameworks like Spring Data JPA with @Transactional, 
you typically want to disable auto-commit at the pool level to allow the framework to handle transactions correctly.

How to disable auto-commit

In Spring Boot (using application.properties or application.yml):
Add the following line to your configuration file:
spring.datasource.hikari.auto-commit=false

With Hibernate:

You may also need to tell Hibernate that the connection provider has already handled this:
spring.jpa.properties.hibernate.connection.provider_disables_autocommit=true

#### JDBC auto commit
https://www.baeldung.com/java-jdbc-auto-commit

