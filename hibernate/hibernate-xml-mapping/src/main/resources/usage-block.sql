select
    file_id,
    block_id,
    block_id + blocks - 1   end_block,
    blocks,
    owner,
    segment_name,
    partition_name,
    segment_type
from
    dba_extents
where
        tablespace_name = upper('GE_TABLESPACE_DEV01')
union all
select
    file_id,
    block_id,
    block_id + blocks - 1   end_block,
    blocks,
    'free'                  owner,
    'free'                  segment_name,
    null                    partition_name,
    null                    segment_type
from
    dba_free_space
where
        tablespace_name = upper('GE_TABLESPACE_DEV01')
order by
    1,2
;

PURGE TABLESPACE GE_TABLESPACE_DEV01;

ALTER TABLESPACE GE_TABLESPACE_DEV01 COALESCE;

truncate table GE_DEV01.SR_SCHEDULE_VALUE_STRING;
truncate table GE_DEV01.SR_SCHEDULE_VALUE_NUMBER;
truncate table GE_DEV01.SR_SCHEDULE;
truncate table GE_DEV01.SR_WORKSPACE;

delete from GE_DEV01.SR_SCHEDULE;

ALTER DATABASE DATAFILE 'K:\SOFT\ORACLE\ORADATA\ORCL\GE_DEV01.DBF' RESIZE 10M;

-- 3 170 893 824

alter table GE_DEV01.SR_SCHEDULE move tablespace GE_TABLESPACE_DEV01;
alter index GE_DEV01.SYS_C0010004 rebuild;