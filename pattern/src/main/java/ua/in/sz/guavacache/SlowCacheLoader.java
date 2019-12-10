package ua.in.sz.guavacache;

import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SlowCacheLoader extends CacheLoader<String, String> {
	@Override
	public String load(String key) throws Exception {
		log.info("Loading ... ");

		Thread.sleep(2_000);

		String value = String.format("%s=%s", key, "value");

		log.info("Loaded");

		return value;
	}
}
