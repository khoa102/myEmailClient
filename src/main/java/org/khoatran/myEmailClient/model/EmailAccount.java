package org.khoatran.myEmailClient.model;

import javax.mail.Store;
import java.util.Properties;

public class EmailAccount {
    private String address;
    private String password;
    private Properties properties; // hold email configuration
    private Store store; // models a message store and its access protocol, for storing and retrieving messages.

    public EmailAccount(String address, String password) {
        this.address = address;
        this.password = password;
        properties = new Properties();
        properties.put("incomingHost", "imap.gmail.com");       // Host to get
        properties.put("mail.store.protocol", "imaps");         // Protocol to sending

        properties.put("mail.transport.protocol", "smtps");     // Protocol for retrieving
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "trie");
        properties.put("outgoingHost", "smtp.gmail.com");
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProperties() {
        return properties;
    }

    public Store getStore() {
        return store;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
