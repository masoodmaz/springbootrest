package com.example.broker.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ClientConfiguration {
    private static final Duration CONNECTION_TIMEOUT=Duration.ofSeconds(20L);
    private static final Duration READ_TIMEOUT=Duration.ofSeconds(30L);


    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate=new RestTemplateBuilder()
                                        .setConnectTimeout(CONNECTION_TIMEOUT).setReadTimeout(READ_TIMEOUT).build();
        return restTemplate;
    }
}
