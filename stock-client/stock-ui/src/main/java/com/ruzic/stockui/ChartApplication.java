package com.ruzic.stockui;

import com.ruzic.stockui.events.*;
import javafx.application.*;
import javafx.stage.*;
import org.springframework.boot.builder.*;
import org.springframework.context.*;

public class ChartApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(StockUiApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
