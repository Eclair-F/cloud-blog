package com.qw.servergateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Program: cloud-blog
 * @ClassName KeyResolver
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-26 16:19
 * @Version 1.0
 **/
@Configuration
public class ResolverConfig {

    @Bean
    public KeyResolver keyResolverConfig() {
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }

}
