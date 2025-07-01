package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapReplaceAllMain {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "4");

        map.replaceAll(MapReplaceAllMain::toIntegerSchema);

        log.info("{}", map);
    }

    private static String toIntegerSchema(String  k, String v) {
        return "2".equals(v) ? "2+2" : v;
    }
}
