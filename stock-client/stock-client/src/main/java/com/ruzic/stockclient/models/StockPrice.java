package com.ruzic.stockclient.models;

import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPrice {
    private String symbol;
    private double price;
    private LocalDateTime time;
}
