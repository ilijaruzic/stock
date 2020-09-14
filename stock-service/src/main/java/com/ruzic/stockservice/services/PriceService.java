package com.ruzic.stockservice.services;

import com.ruzic.stockservice.models.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

import java.time.*;
import java.util.concurrent.*;

@Service
public class PriceService {
    public Flux<StockPrice> generatePrices(String symbol) {
        return Flux.interval(Duration.ofSeconds(1))
                   .map((price) -> new StockPrice(symbol, randomStockPrice(), LocalDateTime.now()));
    }

    private double randomStockPrice() {
        return ThreadLocalRandom.current().nextDouble(100.0);
    }
}
