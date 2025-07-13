#### BULK LOAD PERFORMANCE IN POSTGRESQL

https://www.cybertec-postgresql.com/en/bulk-load-performance-in-postgresql/
https://www.postgresql.org/docs/9.3/pgbench.html
https://medium.com/@dmitry.romanoff/how-to-measure-performance-of-postgresql-database-server-s-b27e2e5130aa

https://github.com/jdbc-observations/datasource-proxy
https://medium.com/@hnasr/postgres-locks-a-deep-dive-9fc158a5641c
https://pganalyze.com/blog/5mins-postgres-find-cause-locking-problems
https://pganalyze.com/blog/postgres-lock-monitoring

https://pganalyze.com/docs/install/self_managed/01_guided_setup
https://pgdash.io/blog/slow-queries-postgres.html
https://www.squash.io/identifying-the-query-holding-the-lock-in-postgres/
https://github.com/postgrespro/pg_wait_sampling
https://www.postgresql.org/docs/current/monitoring-stats.html
https://dev.to/aws-heroes/quick-on-wait-events-from-pgstatactivity-4dkm
https://www.prisma.io/dataguide/postgresql/reading-and-querying-data/optimizing-postgresql
https://zelark.github.io/exploring-query-locks-in-postgres/
https://betterstack.com/community/guides/logging/how-to-start-logging-with-postgresql/
https://www.2ndquadrant.com/en/blog/tracing-postgresql-perf/
https://www.highgo.ca/2019/10/03/trace-query-processing-internals-with-debugger/

pg_wait_sampling - https://github.com/postgrespro/pg_wait_sampling
pg_activity - https://manpages.ubuntu.com/manpages/mantic/man1/pg_activity.1.html
PgHero is an open source PostgreSQL monitoring tool that was developed by Instacart.

Connection tracing and wait event analysis are advanced techniques 
that allow for a deeper understanding of what operations are causing delays. 
By examining the wait events during query execution, one can pinpoint where the query is spending most of its time.

https://stackoverflow.com/questions/26489244/how-to-detect-query-which-holds-the-lock-in-postgres

#### datasource-proxy
https://dev.to/jhatcher9999/a-tale-of-two-connection-pools-bld
https://jdbc-observations.github.io/datasource-proxy/docs/snapshot/user-guide/#about
https://github.com/ttddyy/datasource-proxy-examples
https://github.com/jdbc-observations/datasource-proxy

#### allowPoolSuspension
https://github.com/brettwooldridge/HikariCP
https://github.com/brettwooldridge/HikariCP/issues/1060#issuecomment-356301840

