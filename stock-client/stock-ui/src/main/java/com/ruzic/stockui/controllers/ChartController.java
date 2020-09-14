package com.ruzic.stockui.controllers;

import com.ruzic.stockclient.clients.*;
import com.ruzic.stockui.controllers.consumers.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.chart.*;
import org.springframework.stereotype.*;

@Component
public class ChartController {
    @FXML
    public LineChart<String, Double> chart;
    private StockClient client;

    public ChartController(StockClient client) {
        this.client = client;
    }

    @FXML
    public void initialize() {
        String symbol1 = "SYMBOL1";
        PriceSubscriber subscriber1 = new PriceSubscriber(symbol1);
        client.pricesFor(symbol1)
              .subscribe(subscriber1);

        String symbol2 = "SYMBOL2";
        PriceSubscriber subscriber2 = new PriceSubscriber(symbol2);
        client.pricesFor(symbol2)
              .subscribe(subscriber2);

        String symbol3 = "SYMBOL3";
        PriceSubscriber subscriber3 = new PriceSubscriber(symbol3);
        client.pricesFor(symbol3)
              .subscribe(subscriber3);

        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        data.add(subscriber1.getSeries());
        data.add(subscriber2.getSeries());
        data.add(subscriber3.getSeries());
        chart.setData(data);
    }
}
