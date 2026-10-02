package com.example.productapi.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        // One app instance: in-memory caching avoids adding a Redis service for development.
        // Defer puts and evictions until commit, so rollbacks preserve the previous cache.
        return new TransactionAwareCacheManagerProxy(new ConcurrentMapCacheManager("products"));
    }
}
