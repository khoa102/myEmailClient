package org.khoatran.optionWindow;

public enum ColorTheme {
    LIGHT("/stylesheets/themes/themeLight.css"),
    DEFAULT("/stylesheets/themes/themeDefault.css"),
    DARK("/stylesheets/themes/themeDark.css");

    private final String cssPath;

    ColorTheme(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getCssPath() {
        return cssPath;
    }
}
