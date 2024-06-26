package com.minhle.leeservice.config;

import org.springdoc.core.models.GroupedOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("lee-services")
                .pathsToMatch("/api/**")
                .displayName("Lee Services")
                .build();
    }
}
