package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class Application {
	public static void main(String[] args) throws IOException {
		DefaultCacheManager cacheManager = new DefaultCacheManager(Application.class.getResourceAsStream("/weatherapp-infinispan.xml"));
		Cache<String, String> cache = cacheManager.getCache("weather");

		for (int i = 0; i < 10_000; i++) {
			cache.put(String.format("key-%s", i), String.format("value-%s", i));
		}


/*		for (int i = 0; i < 1_000_000; i++) {
			List<Map.Entry<String, String>> entities = cache.entrySet().stream().toList();
			if (entities.isEmpty()) {
				throw new IllegalStateException();
			}
		}*/

		for (int i = 0; i < 100; i++) {
			for (Map.Entry<String, String> e : cache.entrySet()) {
				if (e == null) {
					throw new IllegalStateException();
				}
			}

		}

		log.info("end");
	}
}