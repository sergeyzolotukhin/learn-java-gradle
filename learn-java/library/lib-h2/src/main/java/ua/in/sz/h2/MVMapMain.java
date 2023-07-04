package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;

@Slf4j
public class MVMapMain {
    public static void main(String[] args) {
        MVStore s = MVStore.open(null);

        MVMap<Integer, String> map = s.openMap("data");

        map.put(1, "Hello");
        map.put(2, "World");

        long oldVersion = s.getCurrentVersion();
        s.commit(); // from now on, the old version is read-only

        log.info("Map: {}", map);

        map.put(1, "Hi");
        map.remove(2);

        MVMap<Integer, String> oldMap = map.openVersion(oldVersion);

        log.info("Value[1] [{}] on version {}", oldMap.get(1), oldVersion);
        log.info("Value[2] [{}] on version {}", oldMap.get(2), oldVersion);

        log.info("Value[1] [{}] on current version {}", map.get(1), s.getCurrentVersion());
        s.commit();

        log.info("Map: {}", map);

        s.rollbackTo(oldVersion);
        log.info("Value[1] [{}] on current version {}", map.get(1), s.getCurrentVersion());

        log.info("Map: {}", map);

        s.close();
    }
}
