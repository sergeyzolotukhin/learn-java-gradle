package ua.in.sz.ehcache.loader.impl;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import ua.in.sz.ehcache.loader.Loader;

import java.util.Optional;

@Slf4j
public class CacheLoader implements Loader {
    private final Loader delegate;
    private final Cache cache;

    public CacheLoader(Loader delegate, CacheManager cacheManager) {
        this.delegate = delegate;
        this.cache = cacheManager.getCache("products", String.class, char[].class);
    }

    @Override
    public char[] load(String key) {
        return Optional.ofNullable(cache.get(key))
                .map(char[].class::cast)
                .orElseGet(() -> doLoad(key));
    }

    private char[] doLoad(String key) {
        char[] data = delegate.load(key);
        cache.put(key, data);
        return data;
    }
}
