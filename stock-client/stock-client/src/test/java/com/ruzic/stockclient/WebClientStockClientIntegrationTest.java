package com.ruzic.stockclient;

import com.ruzic.stockclient.clients.*;
import com.ruzic.stockclient.models.*;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

class WebClientStockClientIntegrationTest {
    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        WebClientStockClient client = new WebClientStockClient(webClient);

        Flux<StockPrice> prices = client.pricesFor("SYMBOL");

        Assertions.assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertEquals(5, fivePrices.count().block());
        Assertions.assertEquals("SYMBOL", fivePrices.blockFirst().getSymbol());
    }
}