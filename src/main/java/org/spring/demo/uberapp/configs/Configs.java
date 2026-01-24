package org.spring.demo.uberapp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Configs {

    @Bean
    RestClient restClient(){
        return RestClient.builder().build();
    }

}
