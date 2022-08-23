package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class Application {
	public static void main(String[] args) throws IOException {
		DefaultCacheManager cacheManager = new DefaultCacheManager(Application.class.getResourceAsStream("/weatherapp-infinispan.xml"));
		Cache<String, String> cache = cacheManager.getCache("weather");
		cache.put("1", "One");
		cache.put("2", "Two");

		for (int i = 0; i < 1_000_000; i++) {
			List<Map.Entry<String, String>> entities = cache.entrySet().stream().toList();
			if (entities.isEmpty()) {
				throw new IllegalStateException();
			}
		}
		log.info("1={}", cache.get("1"));
	}
}