package org.khoatran.loginWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.khoatran.BaseController;
import org.khoatran.EmailManager;
import org.khoatran.ViewManager;

public class LoginWindowController extends BaseController {

    @FXML
    private Label emailAddressField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label passwordField;

    public LoginWindowController(EmailManager emailManager, ViewManager viewManager, String fxmlName) {
        super(emailManager, viewManager, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("loginButtonAction!!!");
        viewManager.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewManager.closeStage(stage);
    }

}
