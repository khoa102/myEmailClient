package org.khoatran.myEmailClient;

public abstract class BaseController {
    // Required for all application
    protected ViewManager viewManager;
    private final String fxmlName;

    // Application dependencies
    protected EmailManager emailManager;

    public BaseController(EmailManager emailManager, ViewManager viewManager, String fxmlName) {
        this.emailManager = emailManager;
        this.viewManager = viewManager;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
