ALTER USER postgres PASSWORD 'mynewpassword';

drop table EMPLOYEE;
create table EMPLOYEE (
    EMPLOYEE_ID      varchar(11) not null,
    EMPLOYEE_NAME    varchar(100),
    primary key (EMPLOYEE_ID)
);

insert into EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME) values('1','Serhij Zolotukhin');
commit;

delete from EMPLOYEE;
commit;

select *
from EMPLOYEE;