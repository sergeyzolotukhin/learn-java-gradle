package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;

@Slf4j
public class MVStoreMain {
    public static void main(String[] args) {
        MVStore s = MVStore.open(null);

        MVMap<Integer, String> map = s.openMap("data");
        map.put(1, "Hello World");
        log.info("{}", map.get(1));

        s.close();
    }
}
