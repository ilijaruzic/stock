package com.ruzic.stockclient;

import com.ruzic.stockclient.clients.*;
import com.ruzic.stockclient.models.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.messaging.rsocket.*;
import reactor.core.publisher.*;
import reactor.test.StepVerifier;

@SpringBootTest
class RSocketClientStockClientIntegrationTest {
    @Autowired
    private RSocketRequester.Builder builder;

    private RSocketRequester createRSocketRequester() {
        return builder.connectTcp("localhost", 5000).block();
    }

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        StockClient client = new RSocketClientStockClient(createRSocketRequester());

        Flux<StockPrice> prices = client.pricesFor("SYMBOL");

        StepVerifier.create(prices.take(5))
                    .expectNextMatches(price -> price.getSymbol().equals("SYMBOL"))
                    .expectNextMatches(price -> price.getSymbol().equals("SYMBOL"))
                    .expectNextMatches(price -> price.getSymbol().equals("SYMBOL"))
                    .expectNextMatches(price -> price.getSymbol().equals("SYMBOL"))
                    .expectNextMatches(price -> price.getSymbol().equals("SYMBOL"))
                    .verifyComplete();
    }
}