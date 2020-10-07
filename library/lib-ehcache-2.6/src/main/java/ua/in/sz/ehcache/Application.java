package ua.in.sz.ehcache;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.time.StopWatch;
import ua.in.sz.ehcache.loader.impl.CacheLoader;
import ua.in.sz.ehcache.loader.Loader;
import ua.in.sz.ehcache.loader.impl.SlowLoader;

@Slf4j
public class Application {
	public static void main(String[] args) {
		CacheManager cacheManager = CacheManager.newInstance();

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

		double average = sw.getTime() / 1000.0 / keys.length;
		log.info("Execution time: {}, average: {} sec", sw, String.format("%.1f", average));

		cacheManager.shutdown();
	}
}