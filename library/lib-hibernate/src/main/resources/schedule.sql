create sequence hibernate_sequence;

insert into SCHEDULE (ID, NAME)
values (-1, 'Name');


CREATE TABLE schedule
(
    ID   INT,
    NAME CHAR(256),
    PRIMARY KEY (ID)
);

drop table schedule;

select count(*)
from schedule;

select *
from schedule
order by id;