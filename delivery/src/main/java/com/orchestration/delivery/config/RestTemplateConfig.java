package com.orchestration.delivery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    RestTemplate getRestTemplate(@Value("${provider.port:8085}") int port) {
        return new RestTemplateBuilder().rootUri(String.format("http://localhost:%d", port)).build();
    }
}
