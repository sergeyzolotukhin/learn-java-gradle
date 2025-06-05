* I recommend to disable a query rewrite capability because I don't wan to get not up today data.
??? I don't want to use ON COMMIT refresh because it can lead to the performance issue when we make a derivation calculation.
* I want to make this table partitioned be time.
* I want to have indexes for a materialized view.

#### Questions
Can we use TUNE_MVIEW package for generate mat. view for a query?

#### Progress
https://docs.oracle.com/en/database/oracle/oracle-database/12.2/dwhsg/basic-materialized-views.html#GUID-51B55F3F-6ABC-4304-9573-BAB08E1E67FF
5.1.7 About Materialized View Schema Design