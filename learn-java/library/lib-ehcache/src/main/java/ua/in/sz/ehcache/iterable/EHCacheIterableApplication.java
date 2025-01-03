package ua.in.sz.ehcache.iterable;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;
import java.util.Objects;

@Slf4j
public class EHCacheIterableApplication {
	@SneakyThrows
	public static void main(String[] args) {
		URL myUrl = Objects.requireNonNull(EHCacheIterableApplication.class.getResource("/ehcache.xml"));
		Configuration xmlConfig = new XmlConfiguration(myUrl);
		CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
		cacheManager.init();

		Cache<String, String> cache = cacheManager.getCache("listeners", String.class, String.class);

		cache.put("1", "one");
		cache.put("2", "two");

		dump(cache);

		cacheManager.close();
	}

	private static void dump(Cache<String, String> cache) {
		for (Cache.Entry<String, String> e : cache) {
			log.info("key: {}, value: {}", e.getKey(), e.getValue());
		}
	}
}
