package ua.in.sz.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.ExecutionException;

@Slf4j
public class Application {
	public static void main(String[] args) throws ExecutionException {
		CacheLoader<String, char[]> loader = new SlowCacheLoader();

		LoadingCache<String, char[]> cache = CacheBuilder.newBuilder()
				.maximumSize(100)
				.softValues()
				.recordStats()
				.build(loader);

		StopWatch sw = StopWatch.createStarted();

		int[] keys = {1, 1, 2, 2, 3, 4, 5, 6, 1, 1};
		for (int i : keys) {
			String key = String.valueOf(i);

			log.info("Getting ... [{}]", key);
			char[] val1 = cache.get(key);
			log.info("Got {}", val1.length);
		}

		sw.stop();

		cache.invalidateAll();

		log.info("Stats: {}, execution time: {}", cache.stats(), sw);
	}
}
