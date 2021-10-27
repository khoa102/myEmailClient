package org.khoatran;

public abstract class BaseController {
    // Required for all application
    protected ViewFactory viewFactory;
    private final String fxmlName;

    // Application dependencies
    protected EmailManager emailManager;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
