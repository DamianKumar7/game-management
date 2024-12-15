package com.game.social.discovery.game_management.Configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate RestTemplateBuilder(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }
}
