package org.khoatran;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.khoatran.loginWindow.LoginWindowController;

import java.io.IOException;

public class ViewFactory {

    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow() {
//        URL firstSceneUrl = getClass().getResource("/org/khoatran/mainWindow/MainWindow.fxml");
        BaseController controller = new LoginWindowController(emailManager, this, "/org/khoatran/loginWindow/LoginWindow.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));

        // This is used to customize the creation of controller injected by javaFX when defining them with fx:controller attribute inside FXML files
        // Using the controller factory instead of setting the controller directly with fxmlLoader.setController() allows us to keep the fx:controller
        // attribute inside FXML files. This makes it easier for IDE to link fxml with controllers and check for errors
        Callback<Class<?>, Object> controllerFactory = type -> {
            // Any controller that needs custom constructor behavior needs to be defined above this check
            if (BaseController.class.isAssignableFrom(type)) {
                // A default behavior for controllerFactory for all classes extends from base controller.
                return controller ;
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
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
