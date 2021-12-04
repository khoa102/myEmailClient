package org.khoatran.mainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import org.khoatran.BaseController;
import org.khoatran.EmailManager;
import org.khoatran.ViewManager;

public class MainWindowController extends BaseController {

    @FXML
    private WebView emailWebView;

    @FXML
    private TableView<?> emailsTableView;

    @FXML
    private TreeView<?> emailsTreeView;

    public MainWindowController(EmailManager emailManager, ViewManager viewManager, String fxmlName) {
        super(emailManager, viewManager, fxmlName);
    }

    @FXML
    void optionAction() {
        viewManager.showOptionWindow();
    }

    @FXML
    public void addAccountAction() {
        viewManager.showLoginWindow();
    }
}

