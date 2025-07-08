#### Advanced Queuing (AQ)
https://www.oracle-developer.net/display.php?id=319
https://docs.oracle.com/database/121/ADQUE/aq_intro.htm#ADQUE0100

#### Using Events to Start Jobs
https://oracle-base.com/articles/10g/scheduler-enhancements-10gr2
https://docs.oracle.com/cd/B28359_01/server.111/b28310/scheduse008.htm#ADMIN10020
https://docs.oracle.com/cd/E18283_01/server.112/e17120/scheduse005.htm#CHDHJEFI

https://renenyffenegger.ch/notes/development/databases/Oracle/adminstration/scheduler/event/queue/index
https://www.fatihacar.com/blog/what-is-lightweight-job-in-oracle/

https://dba.stackexchange.com/questions/42119/passing-parameters-to-a-procedure-executed-by-dbms-scheduler

#### PL/SQL
https://stackoverflow.com/questions/8348013/can-a-table-variable-be-used-in-a-select-statement-where-clause

#### Control liquibase migration scripts on different environments.
https://medium.com/@knoldus/control-liquibase-migration-scripts-on-different-environments-e1f3d9080ca3

https://thorben-janssen.com/automatic-database-updates-integrating-liquibase/

#### 3 Ways to Run Liquibase
https://www.liquibase.com/blog/3-ways-to-run-liquibase
https://madukajayawardana.medium.com/liquibase-tutorial-managing-database-schema-changes-db8ae0046259
https://www.baeldung.com/liquibase-refactor-schema-of-java-app

#### How to fire liquibase rollback script from spring application
To roll back changes in Liquibase within a Spring Boot application, 
you typically use the Liquibase CLI or Maven/Gradle plugins, 
as Spring Boot itself doesn't offer direct, built-in rollback functionality for Liquibase.

There is a liquibase.rollback-file property in Spring Boot 
which can be used to write a rollback SQL script. 
You'd have to run this SQL by hand when you are rolling back the schema. 
You can try Maven Liquibase Plugin to automate it.

https://runebook.dev/en/articles/spring_boot/application-properties/application-properties.data-migration.spring.liquibase.rollback-file
https://reflectoring.io/database-migration-spring-boot-liquibase/

#### Roll Back the Database or Fix Forward? | Liquibase
https://forum.liquibase.org/t/how-to-rollback-in-liquibase-when-using-docker-for-deployment/3673/3
https://medium.com/@fathyelshemy8/how-to-build-pipeline-to-rollback-liquibase-df9370bbd2dd
https://forum.liquibase.org/t/5-ways-to-fix-a-bad-database-change-in-liquibase/6081

#### Spring Boot Actuator
