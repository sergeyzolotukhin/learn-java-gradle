package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;

@Slf4j
public class Application {
	public static void main(String[] args) throws IOException {
		DefaultCacheManager cacheManager = new DefaultCacheManager(Application.class.getResourceAsStream("/weatherapp-infinispan.xml"));
		Cache<Object, Object> cache = cacheManager.getCache("weather");
		cache.put("1", "One");
		cache.put("2", "Two");
		log.info("1={}", cache.get("1"));

	}
}