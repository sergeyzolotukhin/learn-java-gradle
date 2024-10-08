#####

https://www.timescale.com/learn/designing-your-database-schema-wide-vs-narrow-postgres-tables

#### InfluxDB 3.x

#### InfluxDB 2.x

* An [organization] is a workspace for a group of users. 
* * All dashboards, tasks, buckets, members, etc., belong to an organization.

* A [bucket] is a named location    where time series data is stored

####

* A [Measurement] acts as a container for 
* * tags, 
* * fields,  
* * timestamps. 

* The [tagset] is a dictionary of key-value pairs to store metadata with a point.
* The [fieldset] is a set of typed scalar values—the data being recorded by the point.

####

* A [series] is simply a shortcut for saying
* * measurement
* * tagset
* * retention policy

* A [point] has four components: 
* * a measurement, ?
* * a tagset, 
* * a fieldset, 
* * a timestamp

#### 

The serialization format for points is defined by the [line protocol] 



All points with the same retention policy, measurement, and tagset are members of the same series.

* A [retention policy] configures 
* * how long InfluxDB keeps points (duration), 
* * how many copies of those points are stored in the cluster (replication factor), 
* * and the time range covered by [shard groups] (shard group duration).

#### InfluxDB 1.x

* A [database] is a container for
* * users,
* * points
* * retention policies,

1.x had "databases" while 2.x instead has "buckets" and "organizations"

##### Questions

* write ahead log (WAL)
* TSM files, TSM file layout
* The TSM format is based heavily on log-structured merge-trees.
https://docs.influxdata.com/influxdb/v1/concepts/storage_engine/
* Log Structured Merge Tree
https://medium.com/@dwivedi.ankit21/lsm-trees-the-go-to-data-structure-for-databases-search-engines-and-more-c3a48fa469d2
https://tmsvr.com/how-a-log-structured-merge-tree-database-engine-works/
* * SSTables
https://www.igvita.com/2012/02/06/sstable-and-log-structured-storage-leveldb/
* * * merges SSTables
https://docs.datastax.com/en/cassandra-oss/3.x/cassandra/dml/dmlHowDataMaintain.html
* * * sorting merged
* * * Bloom Filters

##### URL

https://stackoverflow.com/questions/44971676/influxdb-data-structure-database-model
https://stackoverflow.com/questions/58190272/what-are-series-and-bucket-in-influxdb
https://docs.influxdata.com/influxdb/cloud-serverless/write-data/best-practices/schema-design/

#####

font-family: source-serif-pro, Georgia, Cambria, "Times New Roman", Times, serif;
font-size: 20px;