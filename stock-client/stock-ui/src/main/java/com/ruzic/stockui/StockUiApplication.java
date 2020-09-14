package com.ruzic.stockui;

import javafx.application.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class StockUiApplication {
    public static void main(String[] args) {
        Application.launch(ChartApplication.class, args);
    }
}
