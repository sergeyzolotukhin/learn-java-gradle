* I recommend to disable a query rewrite capability because I don't wan to get not up today data.
??? I don't want to use ON COMMIT refresh because it can lead to the performance issue when we make a derivation calculation.
* I want to make this table partitioned be time.
* I want to have indexes for a materialized view.

# build method
BUILD IMMEDIATE
DEFERRED

# refresh method
FAST
FORCE
COMPLETE
NEVER

fast refresh -  An operation that applies only the data changes to a materialized view, 
                thus eliminating the need to rebuild the materialized view from scratch.

# when refresh
ON COMMIT
ON DEMAND
ON STATEMENT

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

[5.2.2.1 Materialized Join Views FROM Clause Considerations]
If the materialized view contains only joins, the ROWID columns for each table 
(and each instance of a table that occurs multiple times in the FROM list) 
must be present in the SELECT list of the materialized view.

[5.3 Creating Materialized Views]
If the materialized view contains many rows, then, if appropriate, 
the materialized view should be partitioned (if possible) 
and should match the partitioning of the largest or most frequently updated detail or fact table

[5.3.7.1 About Refresh Modes for Materialized Views]
When using the ON STATEMENT or ON COMMIT method, the time to complete a DML or commit may be slightly longer than usual
Therefore, these methods may not be suitable if many users are concurrently changing the tables upon which the materialized view is based.

[5.3.7.4 General Restrictions on Fast Refresh]

[5.3.7.5 Restrictions on Fast Refresh on Materialized Views with Joins Only]


#### Questions
Can we use TUNE_MVIEW package for generate mat. view for a query?
Do we need to compress this table ? 
What is  the table supports partition change tracking (PCT) ?
SCN-based materialized view logs

#### Examples
[5.2.2 About Materialized Views Containing Only Joins]
https://docs.oracle.com/en/database/oracle/oracle-database/12.2/dwhsg/basic-materialized-views.html#GUID-1F42F25D-739B-4FEE-BEBC-212869D5CD10__i1006694


#### Progress
https://docs.oracle.com/en/database/oracle/oracle-database/12.2/dwhsg/basic-materialized-views.html#GUID-51B55F3F-6ABC-4304-9573-BAB08E1E67FF
5.1.7 About Materialized View Schema Design     [skipped]
5.1.8 About Loading Data into Data Warehouses   [skipped]
5.2.3 About Nested Materialized Views           [skipped]
5.3.3 About Storage And Table Compression for Materialized Views    [skipped]
5.3.6 About Query Rewrite Restrictions    [skipped]
5.3.7.3 About Using Trusted Constraints and Materialized View Refresh   [skipped]
5.3.7.6 Restrictions on Fast Refresh on Materialized Views with Aggregates   [skipped]
5.3.7.7 Restrictions on Fast Refresh on Materialized Views with UNION ALL   [skipped]

-> 5.3.7.8 About Achieving Refresh Goals