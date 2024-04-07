Hello colleagues.

I want to share with you one idea of how to a client can dramatically reduce database workload during a derivation calculation.
This proposal makes sense only if we have a bottleneck on this level.

I propose to provide fine-grained control over the partitioning of the heavy tables for a client.
Also, I propose to provide a fine-grained control over the retention policy of the heavy tables for a client.

We can achieve that via the following steps on the product level:

* Identify a heavy tables. In our case, it can be a SCHEDULE, SCHEDULE_VALUE, and DERIVATION_DATUM table for example.
* Add a single new column in these tables that will be responsible for explicitly specifying a database partition number.
* Make these tables partitioned on the database level.
* Create a Java interface at the application level which will be responsible for calculating the partition number at runtime.
* Use this interface at the following places:
    * When we store data in the database. In our case, it will be a saveSchedule and saveDerivation methods
    * When we query data from the database at hotspot points. In our case, it will be a fetchDerivationDatum method.
* Create the Quartz job that will be responsible for updating a partition number when data is no more actual.

A client needs to take the following steps on his side:

* Divide the dataset into logical groups based on the amount of data, time of actuality, and frequency of usage.
* Divide all his derivations based on these logical groups
* Create a Java class that will calculate a partition number based on these logical groups.
* Create a Java class that will update a partition number when data is no more actual and attach it to the Quartz job.

As a result, we will have the following benefits:

* The client will be responsible for the performance tuning of the data storage level.
* We don't need to do deep optimization of the derivation calculation process.
* We need to make minimal changes on our side.