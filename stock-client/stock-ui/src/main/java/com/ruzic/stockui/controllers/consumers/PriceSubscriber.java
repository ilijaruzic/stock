package com.ruzic.stockui.controllers.consumers;

import com.ruzic.stockclient.models.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.chart.*;

import java.util.function.*;

public class PriceSubscriber implements Consumer<StockPrice> {
    private ObservableList<XYChart.Data<String, Double>> seriesData;
    private XYChart.Series<String, Double> series;

    public PriceSubscriber(String symbol) {
        seriesData = FXCollections.observableArrayList();
        series = new XYChart.Series<>(symbol, seriesData);
    }

    public XYChart.Series<String, Double> getSeries() {
        return series;
    }

    @Override
    public void accept(StockPrice price) {
        Platform.runLater(() -> seriesData.add(new XYChart.Data<>(String.valueOf(price.getTime().getSecond()),
                                                                  price.getPrice())));
    }
}