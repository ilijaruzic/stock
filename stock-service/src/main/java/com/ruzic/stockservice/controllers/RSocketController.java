package com.ruzic.stockservice.controllers;

import com.ruzic.stockservice.models.*;
import com.ruzic.stockservice.services.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

@Controller
public class RSocketController {
    private PriceService priceService;

    public RSocketController(PriceService priceService) {
        this.priceService = priceService;
    }

    @MessageMapping("stock-prices")
    public Flux<StockPrice> prices(String symbol) {
        return priceService.generatePrices(symbol);
    }
}
