package org.khoatran;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.khoatran.loginWindow.LoginWindowController;
import org.khoatran.mainWindow.MainWindowController;

import java.io.IOException;

public class ViewFactory {

    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow() {
        System.out.println("Show Login Window Called");
        initializeStage("/org/khoatran/loginWindow/LoginWindow.fxml");
    }

    public void showMainWindow() {
        System.out.println("Show Main Window Called");
        initializeStage("/org/khoatran/mainWindow/MainWindow.fxml");
    }

    public void showOptionWindow() {
        System.out.println("Show Option Window Called");
        initializeStage("/org/khoatran/optionWindow/OptionWindow.fxml");
    }

    public void initializeStage(String fxmlName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));

        // This is used to customize the creation of controller injected by javaFX when defining them with fx:controller attribute inside FXML files
        // Using the controller factory instead of setting the controller directly with fxmlLoader.setController() allows us to keep the fx:controller
        // attribute inside FXML files. This makes it easier for IDE to link fxml with controllers and check for errors
        Callback<Class<?>, Object> controllerFactory = type -> {
            // Any controller that needs custom constructor behavior needs to be defined above this check
            if (BaseController.class.isAssignableFrom(type)) {
                // A default behavior for controllerFactory for all classes extends from base controller.
                try {
                    return type.getDeclaredConstructor(EmailManager.class, ViewFactory.class, String.class).newInstance(emailManager, this, fxmlName);
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc); // fatal, just bail...
                }
            } else {
                // default behavior for controllerFactory:
                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    throw new RuntimeException(exc); // fatal, just bail...
                }
            }
        };

        fxmlLoader.setControllerFactory(controllerFactory);

        Parent parent;
        try {
            long start = System.currentTimeMillis();
            parent = fxmlLoader.load();
            System.out.println("fmxlLoader load: " + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
    }
}
