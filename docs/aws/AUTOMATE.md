Why automation?
* time (human errors)
* security
* consistency

Options:
* CloudFormation (IaC)
* Elastic Beanstalk - 
* Systems Manager 

AWS CloudFormation
---

Define and provision infrastructure as code (JSON and YAML)

Template - infrastructure description as code
Stack - is a list of resouces which managed as a single unit
StackSet -
Change Sets -

Template
---
required fields:
* Resources

optional fields:
* TemplateFormatVersion
* Parameters
* Mappings
* Output
* Transform

support rollback
immutable architecture ???

AWS Elastic Beanstalk
---
PaaS for
* Java, Node.js, ...
* Tomcat, Docker ...

System Manager
---
