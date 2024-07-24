package id.dojo.accountmanagerjavafx.models;

import javafx.beans.property.SimpleStringProperty;

public class PasswordMap {
    private final SimpleStringProperty modificationTime;
    private final SimpleStringProperty password;

    public PasswordMap(String modificationTime, String password) {
        this.modificationTime = new SimpleStringProperty(modificationTime);
        this.password = new SimpleStringProperty(password);
    }

    public String getModificationTime() {
        return modificationTime.get();
    }

    public SimpleStringProperty modificationTimeProperty() {
        return modificationTime;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
}
