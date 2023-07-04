select m.*
from v$sql_monitor m
where m.username like 'GE_%'
  and m.dbop_name is not null
;

SELECT
    DBMS_SQL_MONITOR.report_sql_monitor( dbop_name => 'load-number-value-2', type => 'TEXT', report_level => 'ALL') AS report
FROM dual