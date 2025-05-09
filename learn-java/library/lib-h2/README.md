MVStore  https://www.h2database.com/html/mvstore.html

=======================================================================================================================
Database
    -> Store
            Map<String, MVTable> tableMap
            MVStore mvStore;
                Map<Integer, MVMap<?, ?>> maps

            M openMap(String name, MVMap.MapBuilder<M, K, V> builder)
        -> MVTable
                List<Index> indexes
            MVPrimaryIndex
                mapName = "table." + getId()
                TransactionMap<Long, SearchRow> dataMap

                void add(SessionLocal session, Row row)

                -> TMVMap extends MVMap

MVMap
    -> RootReference<K,V> root
            Page<K,V> root;

Page
        K[] keys;
    <- Leaf
        private V[] values;
    <- NonLeaf
         PageReference<K,V>[] children;


https://www.h2database.com/html/performance.html#fast_import
https://www.baeldung.com/h2-embedded-db-data-storage
https://h2database.com/javadoc/org/h2/tools/Restore.html

#### How to restore state of H2 database in integration tests

##### Use case: run single test on the local environment

if (backup not exists) { 
* create persistence H2 database (file)
* init database (Liquebase)
* Backup database to file (BACKUP -> zip)
}

if (database file is exists) {
* remove exists file
}

* Restore database from file (Restore.class -> zip)
* Execute test

##### Use case: run several tests with database clean up for each tests

https://www.h2database.com/html/features.html#execute_sql_on_connection
jdbc:h2:mem:test;INIT=runscript from '~/create.sql'\\;runscript from '~/init.sql'

https://stackoverflow.com/questions/9601038/saving-in-memory-h2-database-to-disk
https://h2database.com/html/commands.html#script
SCRIPT TO 'fileName'

Another option is to create another database, 
link the tables with the in-memory database (using create linked table or the link_schema function), 
and then use create table as select to persist the tables.
https://h2database.com/html/functions.html#link_schema
https://h2database.com/html/commands.html#create_linked_table
SELECT * FROM LINK_SCHEMA('TEST2', '', 'jdbc:h2:./test2', 'sa', 'sa', 'PUBLIC');

Fast Database Import
If you have to import a lot of rows, use a PreparedStatement or use CSV import. 
Please note that CREATE TABLE(...) ... AS SELECT ... is faster than CREATE TABLE(...); INSERT INTO ... SELECT ....

https://h2database.com/html/mvstore.html

#### Architecture

Database 
    -> Store
        * MVStore mvStore
        * transactionStore
          * MVStore mvStore
        * tableMap

MVTable
    * Store
    * MVPrimaryIndex primaryIndex
    * List<Index> indexes

MVStore
    Map<Integer, MVMap<?, ?>> maps