package io.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class RoutesConfiguration {

    @Bean
    public RouterFunction<ServerResponse> get(Service service) {
        return route(GET("/initial"), req -> ServerResponse
                .ok().body(service.getData(), GenericDTO.class));
    }
}
