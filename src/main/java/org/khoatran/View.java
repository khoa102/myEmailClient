package org.khoatran;

/**
 *  The View class holds the window of the application and the fxmlPath for the window. This makes it more typesafe and prevents error with fxml path
 *  We may add additional flag for different view here. For example: isCacheable
 */
public enum View {
    LOGIN("/org/khoatran/loginWindow/LoginWindow.fxml"),
    MAIN("/org/khoatran/mainWindow/MainWindow.fxml"),
    OPTION("/org/khoatran/optionWindow/OptionWindow.fxml");

    private final String fxmlPath;

    View(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
