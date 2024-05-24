#####

https://www.timescale.com/learn/designing-your-database-schema-wide-vs-narrow-postgres-tables

InfluxDB

* A [database] is a container for
* * users,
* * points
* * retention policies,

* A [series] is simply a shortcut for saying
* * measurement
* * tagset
* * retention policy

All points with the same retention policy, measurement, and tagset are members of the same series.

* A [Measurement] acts as a container for 
* * tags, 
* * fields,  
* * timestamps. 

* A [point] has four components: 
* * a measurement, ?
* * a tagset, 
* * a fieldset, 
* * a timestamp

* The [tagset] is a dictionary of key-value pairs to store metadata with a point.
* The [fieldset] is a set of typed scalar values—the data being recorded by the point.

The serialization format for points is defined by the [line protocol] 

* A [retention policy] configures 
* * how long InfluxDB keeps points (duration), 
* * how many copies of those points are stored in the cluster (replication factor), 
* * and the time range covered by [shard groups] (shard group duration).

##### Questions

* write ahead log (WAL)
* TSM files, TSM file layout
* The TSM format is based heavily on log-structured merge-trees.

##### URL

https://stackoverflow.com/questions/44971676/influxdb-data-structure-database-model
