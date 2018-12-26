package io.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
public class Service {

    private WebClient webClient;

    public Service(WebClient serviceWebClient) {
        this.webClient = serviceWebClient;
    }

    public Mono<GenericDTO> getData() {
        return webClient.get()
                .uri("/data")
                .exchange()
                .flatMap(resp -> {
                    log.info("Got response");
                    return resp.bodyToMono(new ParameterizedTypeReference<GenericDTO>() {
                    });
                })
                .timeout(Duration.ofMillis(500), logTimeout(Mono.just(GenericDTO.DEFAULT_GENERIC_DTO)));
    }

    public static <T> Mono<T> logTimeout(Mono<T> fallback) {
        return fallback.map(fallbackValue -> {
            log.warn("Timed out waiting response");
            return fallbackValue;
        });
    }

}
