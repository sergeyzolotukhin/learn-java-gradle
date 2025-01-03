package ua.in.sz.ehcache.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventFiring;
import org.ehcache.event.EventOrdering;
import org.ehcache.event.EventType;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;
import java.util.EnumSet;
import java.util.Objects;

/**
 * <a href="https://www.ehcache.org/documentation/3.10/cache-event-listeners.html">...</a>
 */
@Slf4j
public class EHCacheListenerApplication {
	@SneakyThrows
	public static void main(String[] args) {
		URL myUrl = Objects.requireNonNull(EHCacheListenerApplication.class.getResource("/ehcache.xml"));
		Configuration xmlConfig = new XmlConfiguration(myUrl);
		CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
		cacheManager.init();

		Cache<String, String> cache = cacheManager.getCache("listeners", String.class, String.class);

		ListenerObject listener = new ListenerObject();
		cache.getRuntimeConfiguration().registerCacheEventListener(listener, EventOrdering.ORDERED,
				EventFiring.ASYNCHRONOUS, EnumSet.of(EventType.CREATED, EventType.REMOVED));

		cache.put("1", "one");
		cache.put("2", "two");
		cache.remove("1");
		cache.remove("2");

		cache.getRuntimeConfiguration().deregisterCacheEventListener(listener);

		cache.put("1", "one again");
		cache.remove("1");

		cacheManager.close();
	}

	@Slf4j
	private static class ListenerObject implements CacheEventListener<String, String> {
		@Override
		public void onEvent(CacheEvent<? extends String, ? extends String> event) {
			log.info("onEvent: {}", event);
		}
	}
}
