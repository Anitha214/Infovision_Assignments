package com.UserService.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheControl customCacheControl(){
        return CacheControl.noStore();
    }
}
