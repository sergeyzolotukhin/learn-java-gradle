The Identity and Access Management (IAM) - works on global level (not region )
* authentication 
* authorization

#### Authentication
* users
    A user can authenticate with  
        * password 
        * access key
* roles

Roles authenticate an EC2 instance; a user should be used for everything else.

Differences between root user, IAM user, and IAM role

Differences between root user, IAM user, and IAM role
                                            Root user               IAM user    IAM role
Can have a password                         Always                  Yes         No
Can have an access key                      Yes (not recommended)   Yes         No
Can belong to a group                       No                      Yes         No
Can be associated with an EC2 instance      No                      No          Yes


#### authorization 
* policies

A policy contains one or more statements. 
A statement can either allow or deny specific actions on specific resources
Deny overrides Allow

There are two types of policies
* Managed policy    - policies that can be reused
* Inline policy     - A policy that belongs to a certain IAM role, user, or group

#### Questions
How to use a groups ?
How to use a role ?
