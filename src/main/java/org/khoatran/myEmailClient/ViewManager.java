package org.khoatran.myEmailClient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.khoatran.myEmailClient.optionWindow.ColorTheme;
import org.khoatran.myEmailClient.optionWindow.FontSize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewManager {

    private EmailManager emailManager;

    private final Map<View, Parent> cachedView = new HashMap<>();
    private final List<Stage> activeStages;

    public ViewManager(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStages = new ArrayList<>();
    }

    public void showLoginWindow() {
        System.out.println("Show Login Window Called");
        initializeStage(View.LOGIN);
    }

    public void showMainWindow() {
        System.out.println("Show Main Window Called");
        initializeStage(View.MAIN);
    }

    public void showOptionWindow() {
        System.out.println("Show Option Window Called");
        initializeStage(View.OPTION);
    }

    public void initializeStage(View view) {
        Parent parent = getView(view);

        if (parent != null) {
            // Create a new Pane to serve as the root node because a Node can only be set as a root of one scene.
            // If the Parent that is loaded from FXML is set as the root node, we cannot reopen that window after the first time. This is because we create a new scene
            // every time and reuse the Parent as root multiple times.
            // Use anchorPane and anchor the parent node to the four side of the root node to make sure the parent node is stretch with the root node
            AnchorPane root = new AnchorPane();
            root.getChildren().add(parent);
            AnchorPane.setTopAnchor(parent, 0d);
            AnchorPane.setBottomAnchor(parent, 0d);
            AnchorPane.setLeftAnchor(parent, 0d);
            AnchorPane.setRightAnchor(parent, 0d);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            activeStages.add(stage);
        }
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    private Parent getView(View view) {
        Parent parent;
        try {
            long start = System.currentTimeMillis();
            if (cachedView.containsKey(view)) {
                System.out.println("Loading from cache");
                parent = cachedView.get(view);
            } else {
                System.out.println("Loading from FXML");
                parent = loadViewFromFXML(view);
                cachedView.put(view, parent);
            }
            System.out.println("fmxlLoader load: " + (System.currentTimeMillis() - start));
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Parent loadViewFromFXML(View view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view.getFxmlPath()));

        // This is used to customize the creation of controller injected by javaFX when defining them with fx:controller attribute inside FXML files
        // Using the controller factory instead of setting the controller directly with fxmlLoader.setController() allows us to keep the fx:controller
        // attribute inside FXML files. This makes it easier for IDE to link fxml with controllers and check for errors
        Callback<Class<?>, Object> controllerFactory = type -> {
            // Any controller that needs custom constructor behavior needs to be defined above this check
            if (BaseController.class.isAssignableFrom(type)) {
                // A default behavior for controllerFactory for all classes extends from base controller.
                try {
                    return type.getDeclaredConstructor(EmailManager.class, ViewManager.class, String.class).newInstance(emailManager, this, view.getFxmlPath());
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
        return fxmlLoader.load();
    }

    // View option handling
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    @SuppressWarnings("ConstantConditions")
    public void updateStyles() {
        for (Stage stage : activeStages) {
            Scene scene = stage.getScene();
            // handle the css
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(colorTheme.getCssPath()).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(fontSize.getCssPath()).toExternalForm());

        }
    }
}
