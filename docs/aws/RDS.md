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

Other
---
Redshift - data warehouse - OLAP