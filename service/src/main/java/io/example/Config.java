package io.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    public WebClient chatServiceClient(WebClient.Builder builder) {
        return builder.baseUrl("http://127.0.0.1:6060").build();
    }

}
