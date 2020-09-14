package com.ruzic.stockclient.clients;

import com.ruzic.stockclient.models.*;
import reactor.core.publisher.*;

public interface StockClient {
    Flux<StockPrice> pricesFor(String symbol);
}
