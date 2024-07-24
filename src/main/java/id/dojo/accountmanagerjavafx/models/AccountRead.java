package id.dojo.accountmanagerjavafx.models;

import java.io.Serializable;

public class AccountRead implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountName;
    private String signinUrl;
    private String username;
    private String password;

    public AccountRead(){}

    public AccountRead(String accountName, String signinUrl, String username, String password){
        this.accountName = accountName;
        this.signinUrl = signinUrl;
        this.username = username;
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSigninUrl() {
        return signinUrl;
    }

    public void setSigninUrl(String signinUrl) {
        this.signinUrl = signinUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
