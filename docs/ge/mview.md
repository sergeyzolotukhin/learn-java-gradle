* I recommend to disable a query rewrite capability because I don't wan to get not up today data.
??? I don't want to use ON COMMIT refresh because it can lead to the performance issue when we make a derivation calculation.
* I want to make this table partitioned be time.
* I want to have indexes for a materialized view.

# build method
BUILD IMMEDIATE
DEFERRED

# refresh method
FAST
REFRESH FORCE

fast refresh -  An operation that applies only the data changes to a materialized view, 
                thus eliminating the need to rebuild the materialized view from scratch.

# when refresh
ON COMMIT
ON DEMAND

#### Limitation
[5.2 Types of Materialized Views]
You cannot, however, define a materialized view with a subquery in the SELECT list of the defining query.

[5.2.1 About Materialized Views with Aggregates]
Also, materialized view logs must be present on all tables referenced in the query that defines the materialized view.
REFRESH ON COMMIT - The time taken to complete the commit may be slightly longer than usual when this method is chosen.
You can achieve better fast refresh performance for local materialized views if you use a materialized view log that contains a WITH COMMIT SCN clause.

[5.2.2 About Materialized Views Containing Only Joins]
A materialized view log must be present for each detail table.
the ROWID column must be present in each materialized view log.
The rowids of all the detail tables must appear in the SELECT list of the materialized view query definition.

To achieve an optimally efficient refresh, you should ensure that the defining query does not use an outer join that behaves like an inner join. 
If the defining query contains such a join, consider rewriting the defining query to contain an inner join.

#### Questions
Can we use TUNE_MVIEW package for generate mat. view for a query?
Do we need to compress this table ? 
What is  the table supports partition change tracking (PCT) ?

#### Examples
[5.2.2 About Materialized Views Containing Only Joins]
https://docs.oracle.com/en/database/oracle/oracle-database/12.2/dwhsg/basic-materialized-views.html#GUID-1F42F25D-739B-4FEE-BEBC-212869D5CD10__i1006694


#### Progress
https://docs.oracle.com/en/database/oracle/oracle-database/12.2/dwhsg/basic-materialized-views.html#GUID-51B55F3F-6ABC-4304-9573-BAB08E1E67FF
5.1.7 About Materialized View Schema Design     [skipped]
5.1.8 About Loading Data into Data Warehouses   [skipped]

-> 5.2.2 About Materialized Views Containing Only Joins