package com.ruzic.stockclient.clients;

import com.ruzic.stockclient.models.*;
import lombok.extern.log4j.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

import java.io.*;

@Log4j2
public class WebClientStockClient implements StockClient {
    private WebClient webClient;

    public WebClientStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        return webClient.get()
              .uri("http://localhost:8080/stocks/{symbol}", symbol)
              .retrieve()
              .bodyToFlux(StockPrice.class)
              .retry(5)
              .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}
