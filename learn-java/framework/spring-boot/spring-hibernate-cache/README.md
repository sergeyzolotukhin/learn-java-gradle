#### Session factory statistics 

    https://github.com/ehcache/ehcache.org-site/blob/master/ehcache.xml

Statistics[

    start time=1583261751249,
    sessions opened=0,
    sessions closed=0,
    transactions=0,
    successful transactions=0,
    optimistic lock failures=0,
    flushes=0,
    connections obtained=0,
    statements prepared=0,
    statements closed=0,
    
    second level cache puts=0,
    second level cache hits=0,
    second level cache misses=0,
    
    entities loaded=0,
    entities updated=0,
    entities inserted=0,
    entities deleted=0,
    entities fetched=0,
    
    collections loaded=0,
    collections updated=0,
    collections removed=0,
    collections recreated=0,
    collections fetched=0,
    
    naturalId queries executed to database=0,
    naturalId cache puts=0,
    naturalId cache hits=0,
    naturalId cache misses=0,
    naturalId max query time=0,
    
    queries executed to database=0,
    query cache puts=0,
    query cache hits=0,
    query cache misses=0,
    
    update timestamps cache puts=0,
    update timestamps cache hits=0,
    update timestamps cache misses=0,
    
    max query time=0,
    query plan cache hits=0,
    query plan cache misses=0
]

21:08:43.921 [main                ] INFO  | u.i.s.h.cache.JpaCacheApplication        - Starting JpaCacheApplication using Java 20 with PID 123464 (D:\projects-java\_learn-java-gradle\learn-java\framework\spring-boot\spring-hibernate-cache\build\classes\java\main started by szolotukhin in D:\projects-java\_learn-java-gradle)
21:08:43.922 [main                ] INFO  | u.i.s.h.cache.JpaCacheApplication        - The following 2 profiles are active: "dev", "no-framework-log"
21:08:45.293 [main                ] INFO  | u.i.s.h.cache.JpaCacheApplication        - Started JpaCacheApplication in 1.577 seconds (process running for 1.835)

21:08:45.339 [main                ] INFO  | u.in.sz.hibernate.cache.QueryRunner      - QueryRunner first start
21:08:45.565 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Schedule count: 90000, time 225.3 ms
21:08:45.567 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Second level cache puts: 9
21:08:45.567 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Second level cache hits: 89991
21:08:45.567 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Second level cache misses: 9
21:08:45.567 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Entities loaded: 9

21:08:45.568 [main                ] INFO  | u.in.sz.hibernate.cache.QueryRunner      - QueryRunner second start
21:08:45.754 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Schedule count: 90000, time 186.7 ms
21:08:45.755 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Second level cache puts: 9
21:08:45.755 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Second level cache hits: 179991
21:08:45.755 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Second level cache misses: 9
21:08:45.755 [main                ] INFO  | u.i.s.h.c.impl.ScheduleServiceImpl       - Entities loaded: 9

#### Query cache
https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#caching-query-region
https://www.baeldung.com/hibernate-second-level-cache
https://infinispan.org/docs/stable/titles/hibernate/hibernate.html
https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-cache/persistence-cache.html#_controlling_whether_entities_may_be_cached

https://medium.com/@adikale123/listening-and-intercepting-events-hibernate-aaccd04ab451
https://docs.jboss.org/hibernate/core/3.3/reference/en/html/events.html

https://docs.jboss.org/hibernate/core/3.6/reference/en-US/html/performance.html
https://docs.jboss.org/hibernate/orm/4.2/manual/en-US/html/ch20.html

https://www.adeliosys.fr/articles/hibernate-monitoring/
https://vladmihalcea.com/hibernate-statistics/

hibernate.stats.factory =
hibernate.session_factory.statement_inspector =
https://vladmihalcea.com/hibernate-statementinspector/

https://jdbc-observations.github.io/datasource-proxy/docs/snapshot/user-guide/

https://mickaelb.com/post/assert-hibernate-sql-statements-count-in-tests/
https://github.com/Lemick/hibernate-query-asserts/tree/main
https://vladmihalcea.com/source-sql-query-hibernate/

#### EntityGraph
https://thorben-janssen.com/hibernate-performance-tuning/

#### join fetch
https://stackoverflow.com/questions/76393881/fetch-a-collection-in-criteria-api-multiselect
https://www.initgrep.com/posts/java/jpa/select-values-in-criteria-queries

#### autocommit
https://tech.asimio.net/resources/code-snippets/spring-data-jpa-resource-local-hibernate-hikari-turn-off-autocommit/
https://medium.com/@elmansouri.houssam/connections-and-transactions-on-spring-framework-05c71e05eaa6
