EBS - Elastic Block Store

SSD - Solid state drive

General Purpose SSD volumes
* gp2 - default (OS, boot disk/ root device)
* gp3 - high performance - for database, virtual desktops, hadoop - 4 time faster	

Provisioned IOPS SSD volumes
* io2 Block Express 3 - super high performance -  	
* io1 - lagecy

HHD - Hard disk drive 

Throughput Optimized HDD volumes
* st1 - low-cost hdd - can not be a boot volume

Cold HDD volumes
* st2 - lowest cost hdd - use where performace is not important - can not be a boot volume

---
Automatic replicate within a single AZ 
Dynamicy increase, change volume type, with no downtime and performance impact 

Snapshot
---
stored on s3
incremental

exclude caches can be
consistent - recommended stop instance 
share across same regin
encrypted if ebs is encrypted

EBS in same AZ as a EC2 instance

Clone instance in another region
---
volume -> snapshot -> copy to target region -> create image (AMI) - launch instance 
copy unencrypted snapshot can be encrypted

How to encrypted volume
---
volume -> snapshot -> copy with encryption -> create image (AMI) - launch instance 

data moving from volume to EC2 instance is encrypted if volume is encrypted