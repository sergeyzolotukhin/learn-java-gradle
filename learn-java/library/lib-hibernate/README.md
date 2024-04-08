####  

    https://www.ibm.com/developerworks/ru/library/j-typesafejpa/index.html

#### Hibernate 
https://docs.jboss.org/hibernate/orm/6.3/quickstart/html_single/

https://vladmihalcea.com/the-anatomy-of-hibernate-dirty-checking/

#### PostgreSQL - Database Physical Storage

Every table is stored as an array of pages of a fixed size (usually 8 kB)

There are five parts to each page 
# PageHeaderData    - 24 bytes long
# ItemIdData        - Array of (offset,length) pairs pointing to the actual items. 4 bytes per item.
# Free space        - The unallocated space. New item pointers are allocated from the start of this area, new items from the end.
# Items             - The actual items themselves.
# Special space	    - Empty in ordinary tables.

https://www.postgresql.org/docs/9.1/storage-page-layout.html

Numeric values are physically stored without any extra leading or trailing zeroes. 
Thus, the declared precision and scale of a column are maximums, not fixed allocations. 
(In this sense the numeric type is more akin to varchar(n) than to char(n).) 
The actual storage requirement is two bytes for each group of four decimal digits, plus eight bytes overhead.

https://www.postgresql.org/docs/8.1/datatype.html#DATATYPE-NUMERIC-DECIMAL

pg_column_size(any)	int	    Number of bytes used to store a particular value (possibly compressed)

https://www.postgresql.org/docs/9.4/functions-admin.html