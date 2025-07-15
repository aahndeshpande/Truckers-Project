package com.aditya.trucker.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimiterConfig {

    @Bean
    public Cache<String, Integer> rateLimiterCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    public static class RateLimit {
        private static final int MAX_REQUESTS_PER_MINUTE = 100;
        private static final int MAX_REQUESTS_PER_HOUR = 1000;

        public static boolean isRequestAllowed(Cache<String, Integer> cache, String key) {
            cache.putIfAbsent(key, 0);
            int currentCount = cache.get(key);

            if (currentCount >= MAX_REQUESTS_PER_MINUTE) {
                return false;
            }

            cache.put(key, currentCount + 1);
            return true;
        }
    }
}
