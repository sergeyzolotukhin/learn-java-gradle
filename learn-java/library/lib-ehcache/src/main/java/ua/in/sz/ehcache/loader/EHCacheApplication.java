package ua.in.sz.ehcache.loader;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import ua.in.sz.ehcache.loader.impl.CacheLoader;
import ua.in.sz.ehcache.loader.impl.SlowLoader;

import java.net.URL;
import java.util.Objects;

/**
 * <a href="https://www.ehcache.org/documentation/3.10/cache-event-listeners.html">...</a>
 */
@Slf4j
public class EHCacheApplication {
	@SneakyThrows
	public static void main(String[] args) {
		URL myUrl = Objects.requireNonNull(EHCacheApplication.class.getResource("/ehcache.xml"));
		Configuration xmlConfig = new XmlConfiguration(myUrl);
		CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
		cacheManager.init();

		Loader slowLoader = new SlowLoader();
		Loader cachedLoader = new CacheLoader(slowLoader, cacheManager);

		StopWatch sw = StopWatch.createStarted();

		int[] keys = {1, 1, 1, 1, 1, 1, 1, 1, 1, 9};
		for (int i : keys) {
			String key = String.valueOf(i);
			char[] value = cachedLoader.load(key);
			log.info("Got {}", value.length);
		}

		sw.stop();

		double average = sw.getDuration().toMillis() / 1000.0 / keys.length;
		log.info("Execution time: {}, average: {} sec", sw, String.format("%.1f", average));

		cacheManager.close();
	}
}
