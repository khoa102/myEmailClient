package org.khoatran;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        StackPane stackPane = new StackPane(label);

//        System.out.println(getClass().getResource("/view/first.fxml"));
        URL firstSceneUrl = getClass().getResource("/org/khoatran/loginWindow/LoginWindow.fxml");

        Parent parent;
        if (firstSceneUrl != null)
            parent = FXMLLoader.load(firstSceneUrl);
        else
            parent = stackPane;

        var scene = new Scene(parent, 510, 325);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}