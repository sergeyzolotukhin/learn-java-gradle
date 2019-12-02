package ua.in.sz.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.lang.NonNull;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Application {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CacheLoader<String, String> loader = new SlowCacheLoader();

		LoadingCache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(100)
//				.expireAfterAccess(Duration.ofMillis(1000))
				.recordStats()
				.build(loader);

		StopWatch sw = StopWatch.createStarted();

		for (int i = 0; i < 4; i++) {
			log.info("Getting ...");
			String val1 = cache.get("1");
			log.info("Got");

//			Thread.sleep(500);
		}

		sw.stop();

		log.info("Stats: {}, time: {}", cache.stats().toString(), sw);
	}
}
