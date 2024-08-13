RDS - Relational Database Service
---

MS SQL Server
Oracle
MySQL
PostgreSQL
MariaDB
Amazon Aurora - Multi-AZ by default

Multi-AZ - automatic replicate on standby 
Automated backup
Automatic switch to standby when failed

Use
---
OLTP

Not use
---
OLAP

Performance
---
Read replica
cross-AZ / some-AZ / cross-region
has own DNS name (endpoint)
read replica can be promoted to separate database 
required automatic backup is enable 
we can heve up to 5 Read replicas 

Aurora
---
Performance 3~5 faster MySQL and PostgreSQL 
store auto scaling
auto scale 
2 copy of DV in each AZ, minimum 3 AZ
Aurora Serverless vs Aurora ???
Snapshot can be share across account 

DynamoDB
---
NoSQL 

store on SSD 
support Document and key-value data model
spread across 3 data centers in different region
eventually (by default) and strong consistency read
transaction should be enabled 
one or more table in single account and region
ACID

Backup
Point in time recovery (35 days)

Global table - replicate - based on stream
Streams - is used in reapplication and in lambda

DAX - DynamoDB Accelerator - is a cache service

DocumentDB
---
MongoDB - document model
DocumentDB is same as mongodb

Keyspaces
---
Cassandra
Keyspaces is same as Cassandra

Other
---
Redshift - data warehouse - OLAP