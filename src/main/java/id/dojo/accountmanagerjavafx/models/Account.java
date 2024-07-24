package id.dojo.accountmanagerjavafx.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.HashMap;

public class Account implements Serializable{

    private StringProperty accountName = new SimpleStringProperty();;
    private StringProperty signinUrl = new SimpleStringProperty();;
    private StringProperty username = new SimpleStringProperty();;
    private StringProperty password = new SimpleStringProperty();;
    private HashMap<String, String> Password = new HashMap<String, String>();

    public Account(){
    }

    public Account(String accountName, String signinUrl, String username, String password){
        this.accountName = new SimpleStringProperty(accountName);
        this.signinUrl = new SimpleStringProperty(signinUrl);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
//        this.Password = Password;
    }

    // NAME
    public String getAccountName() {
        return accountName.get();
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    public StringProperty accountNameProperty(){
        return accountName;
    }

    // PASSWORD

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty(){
        return password;
    }

    // USERNAME

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty(){
        return username;
    }

    // SIGNIN URL

    public String getSiginUrl() {
        return signinUrl.get();
    }

    public void setSigninUrl(String signinUrl) {
        this.signinUrl.set(signinUrl);
    }

    public StringProperty signinProperty(){
        return signinUrl;
    }

    // PASSWORD HASHMAP

    public void setPasswordAll(String password, HashMap<String, String> Password){
        this.password = new SimpleStringProperty(password);
        this.Password = Password;
    }

    public static Account readFileOpen(AccountRead accountRead){
        Account account = new Account();
        account.setAccountName(accountRead.getAccountName());
        account.setUsername(accountRead.getUsername());
        account.setSigninUrl(accountRead.getSigninUrl());
        account.setPassword(accountRead.getPassword());
        return account;
    }

    public AccountRead toAccountData() {
        return new AccountRead(getAccountName(), getSiginUrl(), getUsername(), getPassword());
    }

}
