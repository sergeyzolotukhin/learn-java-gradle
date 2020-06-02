package ua.in.sz.ehcache;

import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class SlowCacheLoader extends CacheLoader<String, char[]> {
	@Override
	public char[] load(String key) {
		log.trace("Loading ... ");

		char[] value = new char[100 * 1024 * 1024];

		Arrays.fill(value, 'A');

		log.info("Loaded");

		return value;
	}
}
