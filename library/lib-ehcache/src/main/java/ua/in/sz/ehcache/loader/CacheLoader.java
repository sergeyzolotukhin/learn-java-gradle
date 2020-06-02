package ua.in.sz.ehcache.loader;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Optional;

@Slf4j
public class CacheLoader implements Loader {
    private final Loader delegate;
    private final Cache cache;

    public CacheLoader(Loader delegate, CacheManager cacheManager) {
        this.delegate = delegate;
        this.cache = cacheManager.getCache("products");
    }

    @Override
    public char[] load(String key) {
        return Optional.ofNullable(cache.get(key))
                .map(Element::getObjectValue)
                .map(char[].class::cast)
                .orElseGet(() -> doLoad(key));
    }

    private char[] doLoad(String key) {
        char[] data = delegate.load(key);
        cache.put(new Element(key, data));
        return data;
    }
}
