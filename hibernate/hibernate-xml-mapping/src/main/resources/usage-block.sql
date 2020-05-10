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