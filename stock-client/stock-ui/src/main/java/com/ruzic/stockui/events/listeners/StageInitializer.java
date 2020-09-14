package com.ruzic.stockui.events.listeners;

import com.ruzic.stockui.events.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;

import java.io.*;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private ApplicationContext applicationContext;
    @Value("classpath:/views/chart.fxml")
    private Resource chartResource;
    private String applicationTitle;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
            fxmlLoader.setControllerFactory(cls -> applicationContext.getBean(cls));
            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 800, 600));
            stage.setTitle(applicationTitle);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
