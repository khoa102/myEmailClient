package org.khoatran.loginWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.khoatran.BaseController;
import org.khoatran.EmailManager;
import org.khoatran.ViewFactory;

public class LoginWindowController extends BaseController {

    @FXML
    private Label emailAddressField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label passwordField;

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void loginButtonAction() {
        System.out.println("loginButtonAction!!!");
        viewFactory.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
