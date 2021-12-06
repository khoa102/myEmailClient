package org.khoatran.myEmailClient.loginWindow;

import com.google.common.base.Strings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.khoatran.myEmailClient.BaseController;
import org.khoatran.myEmailClient.EmailManager;
import org.khoatran.myEmailClient.ViewManager;
import org.khoatran.myEmailClient.model.EmailAccount;
import org.khoatran.myEmailClient.service.EmailLoginResult;
import org.khoatran.myEmailClient.service.LoginService;

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
        if (fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);
            EmailLoginResult emailLoginResult = loginService.login();

            switch (emailLoginResult) {
                case SUCCESS:
                    System.out.println("login successfully!" + emailAccount);
                    break;
                default:
                    return;
            }
        }
        System.out.println("loginButtonAction!!!");
        viewManager.showMainWindow();
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        viewManager.closeStage(stage);
    }

    private boolean fieldsAreValid() {
        if (Strings.isNullOrEmpty(emailAddressField.getText())) {
            errorLabel.setText("Please fill email");
            return false;
        }

        if (Strings.isNullOrEmpty(passwordField.getText())) {
            errorLabel.setText("Please fill password");
            return false;
        }
        return true;
    }

}
