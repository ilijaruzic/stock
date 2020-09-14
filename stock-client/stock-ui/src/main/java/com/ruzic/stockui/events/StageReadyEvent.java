package com.ruzic.stockui.events;

import javafx.stage.*;
import org.springframework.context.*;

public class StageReadyEvent extends ApplicationEvent {
    public StageReadyEvent(Stage stage) {
        super(stage);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
