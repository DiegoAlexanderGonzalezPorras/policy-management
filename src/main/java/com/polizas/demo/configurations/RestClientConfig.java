package com.polizas.demo.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${core.api.key}")
    private String coreApiKey;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://6afbad77892984c4494a0ca4.mockapi.io/core-mock/")
                .defaultHeader("x-api-key", coreApiKey)
                .build();
    }
}
