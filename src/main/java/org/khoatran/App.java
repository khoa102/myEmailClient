package org.khoatran;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        ViewManager viewManager = new ViewManager(new EmailManager());
        viewManager.showLoginWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

}