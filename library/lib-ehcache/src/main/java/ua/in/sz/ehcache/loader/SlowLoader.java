package ua.in.sz.ehcache.loader;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SlowLoader implements Loader {
	@Override
	@SneakyThrows
	public char[] load(String key) {
		log.trace("Loading ... ");

		char[] value = new char[0];
		TimeUnit.SECONDS.sleep(2);

		log.info("Loaded");

		return value;
	}
}
