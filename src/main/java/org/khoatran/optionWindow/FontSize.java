package org.khoatran.optionWindow;

public enum FontSize {
    SMALL("/stylesheets/fonts/fontSmall.css"),
    MEDIUM("/stylesheets/fonts/fontMedium.css"),
    BIG("/stylesheets/fonts/fontBig.css");

    private final String cssPath;

    FontSize(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getCssPath() {
        return this.cssPath;
    }
}
