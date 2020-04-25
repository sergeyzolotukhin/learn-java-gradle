package ua.in.sz.spring.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheServiceClient implements Runnable {

	private final CacheableService cacheableService;

	@Override
	@Scheduled(initialDelay = 5_000, fixedDelay = 20_000)
	public void run() {
		log.info("Cache calling");

		for (int i = 0; i < 10; i++) {
			String key = String.format("k-%d", (i % 2));
			String value = cacheableService.get(key);

			log.info("call get({}) => {}", key, value);
		}


		log.info("Cache called");
	}

}
