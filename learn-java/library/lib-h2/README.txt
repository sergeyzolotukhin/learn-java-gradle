MVStore  https://www.h2database.com/html/mvstore.html

=======================================================================================================================
Database
    -> Store
            Map<String, MVTable> tableMap
            MVStore mvStore;
                Map<Integer, MVMap<?, ?>> maps

            M openMap(String name, MVMap.MapBuilder<M, K, V> builder)
        -> MVTable
                List<Index> indexes
            MVPrimaryIndex
                mapName = "table." + getId()
                TransactionMap<Long, SearchRow> dataMap

                void add(SessionLocal session, Row row)

                -> TMVMap extends MVMap

MVMap
    -> RootReference<K,V> root
            Page<K,V> root;

Page
        K[] keys;
    <- Leaf
        private V[] values;
    <- NonLeaf
         PageReference<K,V>[] children;


https://www.h2database.com/html/performance.html#fast_import
