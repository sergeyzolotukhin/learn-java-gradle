package ua.in.sz.spring.cache;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheableServiceImpl implements CacheableService {
	@Override
	@SneakyThrows
	@Cacheable("invocationCache")
	public String get(String key) {
		log.info("get start: [{}]", key);

		TimeUnit.SECONDS.sleep(2);

		log.info("get end: [{}]", key);

		return String.format("key:%s", key);
	}
}
