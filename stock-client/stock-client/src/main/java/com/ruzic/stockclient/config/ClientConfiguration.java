package com.ruzic.stockclient.config;

import com.ruzic.stockclient.clients.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.messaging.rsocket.*;
import org.springframework.web.reactive.function.client.*;

@Configuration
public class ClientConfiguration {
    @Bean
    @Profile("sse")
    public StockClient webClientStockClient(WebClient webClient) {
        return new WebClientStockClient(webClient);
    }

    @Bean
    @Profile("rsocket")
    public StockClient socketClientStockClient(RSocketRequester socketRequester) {
        return new RSocketClientStockClient(socketRequester);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public RSocketRequester socketRequester(RSocketRequester.Builder builder) {
        return builder.connectTcp("localhost", 5000).block();
    }
}
