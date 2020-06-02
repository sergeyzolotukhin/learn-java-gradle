package ua.in.sz.ehcache;

import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.time.StopWatch;

@Slf4j
public class Application {
	public static void main(String[] args) throws Exception {
		CacheLoader<String, char[]> loader = new SlowCacheLoader();

		CacheManager manager = CacheManager.newInstance();

		Cache cache = manager.getCache("products");

		StopWatch sw = StopWatch.createStarted();

		int[] keys = {1, 1, 2, 2, 3, 4, 5, 6, 1, 1};
		for (int i : keys) {
			String key = String.valueOf(i);

			log.info("Getting ... [{}]", key);
			Element element = cache.get(key);
			char[] val1;
			if (element == null) {
				val1 = loader.load(key);
				cache.put(new Element(key, val1));
			} else {
				val1 = (char[]) element.getObjectValue();
			}
			log.info("Got {}", val1.length);
		}

		sw.stop();

		manager.shutdown();

		log.info("Execution time: {}", sw);
	}
}
