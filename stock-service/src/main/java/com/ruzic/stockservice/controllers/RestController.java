package com.ruzic.stockservice.controllers;

import com.ruzic.stockservice.models.*;
import com.ruzic.stockservice.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private PriceService priceService;

    public RestController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = { "/stocks/{symbol}" },
                produces = { MediaType.TEXT_EVENT_STREAM_VALUE })
    public Flux<StockPrice> prices(@PathVariable String symbol) {
        return priceService.generatePrices(symbol);
    }
}
