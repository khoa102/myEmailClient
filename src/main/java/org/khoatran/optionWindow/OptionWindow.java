package org.khoatran.optionWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import org.khoatran.BaseController;
import org.khoatran.EmailManager;
import org.khoatran.ViewFactory;

public class OptionWindow extends BaseController {

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<?> themePicker;

    public OptionWindow(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void applyButtonAction(ActionEvent event) {

    }

    @FXML
    void cancelButtonAction(ActionEvent event) {

    }

}
