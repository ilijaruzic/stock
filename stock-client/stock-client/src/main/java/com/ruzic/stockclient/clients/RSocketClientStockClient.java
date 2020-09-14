package com.ruzic.stockclient.clients;

import com.ruzic.stockclient.models.*;
import lombok.extern.log4j.*;
import org.springframework.messaging.rsocket.*;
import reactor.core.publisher.*;

import java.io.*;

@Log4j2
public class RSocketClientStockClient implements StockClient {
    private RSocketRequester socketRepquester;

    public RSocketClientStockClient(RSocketRequester socketRepquester) {
        this.socketRepquester = socketRepquester;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        return socketRepquester.route("stock-prices")
                               .data(symbol)
                               .retrieveFlux(StockPrice.class)
                               .retry(5)
                               .doOnError(IOException.class, e -> log.error(e.getMessage()));
    }
}
