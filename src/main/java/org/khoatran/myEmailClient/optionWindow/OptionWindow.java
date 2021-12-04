package org.khoatran.myEmailClient.optionWindow;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.khoatran.myEmailClient.BaseController;
import org.khoatran.myEmailClient.EmailManager;
import org.khoatran.myEmailClient.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionWindow extends BaseController implements Initializable {

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    public OptionWindow(EmailManager emailManager, ViewManager viewManager, String fxmlName) {
        super(emailManager, viewManager, fxmlName);
    }

    @FXML
    void applyButtonAction() {
        viewManager.setColorTheme(themePicker.getValue());
        viewManager.setFontSize(FontSize.values()[(int)fontSizePicker.getValue()]);
        viewManager.updateStyles();
    }

    @FXML
    void cancelButtonAction() {
        Stage stage = (Stage) themePicker.getScene().getWindow();
        viewManager.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpThemePicker();
        setUpFontSizePicker();
    }

    private void setUpFontSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewManager.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double aDouble) {
                int i = aDouble.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener((obj, oldVal, newVal) -> fontSizePicker.setValue(newVal.intValue()));
    }

    private void setUpThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewManager.getColorTheme());
    }
}
