CREATE TABLE schedule
(
    ID   INT,
    NAME CHAR(256),
    PRIMARY KEY (ID)
);

insert into SCHEDULE (ID, NAME) values ( -1, 'Name 1' );
insert into SCHEDULE (ID, NAME) values ( -2, 'Name 2' );

select *
from schedule
;

