CREATE TABLE schedule
(
    ID   INT,
    action_id   INT,
    NAME varchar(256),
    PRIMARY KEY (ID)
);

delete from schedule;
insert into SCHEDULE (ID, NAME, action_id) values ( -1, 'Name 1', -1 );
insert into SCHEDULE (ID, NAME, action_id) values ( -2, 'Name 2', null );

select *
from schedule
;

DROP TABLE schedule;

CREATE TABLE ACTION
(
    ID   INT,
    schedule_id   INT,
    NAME varchar(256),
    PRIMARY KEY (ID)
);

delete from ACTION;
insert into ACTION (ID, NAME, schedule_id) values ( -1, 'Name 1', -1 );
insert into ACTION (ID, NAME, schedule_id) values ( -2, 'Name 2', -1 );

select *
from ACTION
;

DROP TABLE ACTION;

select a1_0.initialize_schedule_entity_id, a1_1.id, a1_1.name
from schedule_actions a1_0
         join action a1_1 on a1_1.id = a1_0.actions_id
where a1_0.initialize_schedule_entity_id=?
;

CREATE TABLE schedule_actions
(
    initialize_schedule_entity_id   INT,
    actions_id   INT
);

delete from schedule_actions;
insert into schedule_actions (initialize_schedule_entity_id, actions_id) values ( -1, -1);
insert into schedule_actions (initialize_schedule_entity_id, actions_id) values ( -1, -2 );

select *
from schedule_actions
;

DROP TABLE schedule_actions;