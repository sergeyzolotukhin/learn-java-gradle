CREATE TABLE schedule
(
    ID   INT,
    NAME varchar(256),
    PRIMARY KEY (ID)
);

delete from schedule;
insert into SCHEDULE (ID, NAME) values ( -1, 'Name 1' );
insert into SCHEDULE (ID, NAME) values ( -2, 'Name 2' );

select *
from schedule
;

DROP TABLE schedule;
