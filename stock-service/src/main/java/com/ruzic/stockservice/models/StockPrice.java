package com.ruzic.stockservice.models;

import java.time.*;
import java.util.*;

public class StockPrice {
    private final String symbol;
    private final double price;
    private final LocalDateTime time;

    public StockPrice(String symbol, double price, LocalDateTime time) {
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        StockPrice that = (StockPrice) object;
        return Double.compare(that.price, price) == 0 &&
               Objects.equals(symbol, that.symbol) &&
               time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price, time);
    }

    @Override
    public String toString() {
        return symbol + ": " + price + " (" + time + ")";
    }
}
