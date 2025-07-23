####  

    https://www.ibm.com/developerworks/ru/library/j-typesafejpa/index.html

#### Hibernate 
https://docs.jboss.org/hibernate/orm/6.3/quickstart/html_single/

https://vladmihalcea.com/the-anatomy-of-hibernate-dirty-checking/

#### PostgreSQL - Database Physical Storage

Every table is stored as an array of pages of a fixed size (usually 8 kB)

There are five parts to each page 
# PageHeaderData    - 24 bytes long
# ItemIdData        - Array of (offset,length) pairs pointing to the actual items. 4 bytes per item.
# Free space        - The unallocated space. New item pointers are allocated from the start of this area, new items from the end.
# Items             - The actual items themselves.
# Special space	    - Empty in ordinary tables.

https://www.postgresql.org/docs/9.1/storage-page-layout.html

Numeric values are physically stored without any extra leading or trailing zeroes. 
Thus, the declared precision and scale of a column are maximums, not fixed allocations. 
(In this sense the numeric type is more akin to varchar(n) than to char(n).) 
The actual storage requirement is two bytes for each group of four decimal digits, plus eight bytes overhead.

https://www.postgresql.org/docs/8.1/datatype.html#DATATYPE-NUMERIC-DECIMAL

pg_column_size(any)	int	    Number of bytes used to store a particular value (possibly compressed)

https://www.postgresql.org/docs/9.4/functions-admin.html

#### PostgreSQL extension for time-series

TimescaleDB is an open-source database designed to make SQL scalable for time-series data. 
    It is engineered up from PostgreSQL and packaged as a PostgreSQL extension
https://github.com/timescale/timescaledb
https://medium.com/@SaiParvathaneni/a-complete-guide-for-postgres-timescale-db-ae75a4d45b8d
https://www.timescale.com/blog/timescaledb-vs-influxdb-for-time-series-data-timescale-influx-sql-nosql-36489299877/
https://medium.datadriveninvestor.com/sql-and-timescaledb-e4676aac38a9

PipelineDB is a PostgreSQL extension for high-performance time-series aggregation, 
    designed to power realtime reporting and analytics applications.

#### PostgreSQL caching
shared_buffers - The default is typically 128 megabytes (128MB)
Each cache entry points to an 8KB block (sometimes called a page) of data
https://klouddb.io/postgres-os-cache-monitoring-blog-final/
https://madusudanan.com/blog/understanding-postgres-caching-in-depth/

#### Batch Insert/Update with Hibernate/JPA

https://www.baeldung.com/jpa-hibernate-batch-insert-update
https://medium.com/@jerolba/persisting-fast-in-database-1af4a281e3a
https://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch15.html
https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate
https://stackoverflow.com/questions/1507758/how-to-map-dynamically-created-table-in-hibernate
https://stackoverflow.com/questions/22493344/in-hibernate-what-the-entity-name-property-does
https://docs.jboss.org/hibernate/orm/3.6/reference/en-US/html/mapping.html#mapping-entityname
https://stackoverflow.com/questions/12741857/is-it-possible-to-provide-more-than-one-mapping-for-a-particular-persistent-clas

#### Embeddable 

Embeddable
CompositeUserType

https://www.baeldung.com/hibernate-custom-types
https://javatutor.net/articles/hibernate-types-udt

#### persist or merge

https://stackoverflow.com/questions/44444524/how-spring-data-jpa-checks-whether-to-perform-update-or-save
https://vladmihalcea.com/jpa-persist-and-merge/
https://www.digitalocean.com/community/tutorials/jpa-entitymanager-hibernate
https://stackoverflow.com/questions/50997657/merge-with-nonexisting-id

#### Hibernate - identifier strategies

https://stackoverflow.com/questions/10041938/how-to-choose-the-id-generation-strategy-when-using-jpa-and-hibernate

The hi/lo algorithm
---
https://vladmihalcea.com/the-hilo-algorithm/
https://stackoverflow.com/questions/60072871/the-hibernate-hilo-strategy-does-not-generate-the-values-according-to-the-next-d
* Hilo limitations - Hilo algorithm is not interoperable with systems that don't know the hilo allocations scheme, 
and this is why Hibernate supports the pooled optimizer.

Hibernate pooled and pooled-lo identifier generators
---
https://vladmihalcea.com/hibernate-hidden-gem-the-pooled-lo-optimizer/
* The major advantage of the pooled optimizers is that they are interoperable with other external systems

pooled-lo: It’s similar to the pooled optimizer but the database sequence value is used as the current in-memory lowest boundary

IdentifierGenerator <|- PersistentIdentifierGenerator
* IncrementGenerator                        -> select max() ...
* TableGenerator                            -> select id from sequence_table where name = ?
* CompositeNestedGeneratedValueGenerator    -> composite pattern
* SequenceStyleGenerator                    -> select sequence.next_value from dual

HiLoOptimizer
PooledOptimizer

SequenceStructure   -> execute sql statement and get result (JDBC) 
    -> return AccessCallback 
TableStructure      -> 


#### 

https://sathiyakugan.medium.com/java-agent-a-powerful-tool-you-might-have-missed-fe6a85884481
https://dzone.com/articles/java-agent-1
https://medium.com/@bdamianchamel/java-agents-b3f3addd5d3e
https://sathiyakugan.medium.com/java-agent-a-powerful-tool-you-might-have-missed-fe6a85884481
https://dev.to/rubyshiv/java-instrumentation-a-simple-working-example-in-java-4adm


ClassFileTransformer

https://www.baeldung.com/jpa-entity-graph

#### Logback

${CONSOLE_LOG_PATTERN:
-
%clr(%d{${LOG_DATEFORMAT_PATTERN:-dd-MM-yy HH:mm:ss.SSS}}){faint} 
%clr(${LOG_LEVEL_PATTERN:-%5p}) 
%clr([%15.15t]){faint} 
%clr(%-40.40logger{39}){cyan} 
%clr(:){faint} 
%m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}
}

#### Prevent spontaneous cache creation
hibernate.javax.cache.missing_cache_strategy 
create

https://www.ehcache.org/blog/2017/03/15/spontaneous-cache-creation.html
https://thorben-janssen.com/hibernate-ehcache/

https://docs.jboss.org/hibernate/orm/6.5/userguide/html_single/Hibernate_User_Guide.html#caching-provider-jcache-missing-cache-strategy

CacheManagerEventListener
@CacheConfig
https://www.baeldung.com/spring-cache-tutorial#5-cacheconfig

#### JPA and Hibernate Cascade Types
https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/

https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate

#### HibernateException: Illegal attempt to associate a collection with two open sessions
https://access.redhat.com/solutions/1354713
#### Java Multithreading with Hibernate Session and Spring
https://medium.com/@orberkov/java-multithreading-with-hibernate-session-and-spring-fb1b38872e0c
https://xebia.com/blog/hibernate-and-multi-threading/

#### Spring Transaction Management Over Multiple Threads
https://dzone.com/articles/spring-transaction-management-over-multiple-thread-1

####  Hibernates Multitenant
https://www.baeldung.com/hibernate-6-multitenancy
https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
https://docs.jboss.org/hibernate/orm/4.2/devguide/en-US/html/ch16.html
https://medium.com/swlh/multi-tenancy-implementation-using-spring-boot-hibernate-6a8e3ecb251a
https://www.baeldung.com/multitenancy-with-spring-data-jpa

#### Logging
https://www.baeldung.com/hibernate-logging-levels
https://www.adeliosys.fr/articles/hibernate-monitoring/


