package org.khoatran.loginWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginWindowController {

    @FXML
    private Label emailAddressField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label passwordField;

    @FXML
    void loginButtonAction() {
        System.out.println("Click Login!");
    }

}
