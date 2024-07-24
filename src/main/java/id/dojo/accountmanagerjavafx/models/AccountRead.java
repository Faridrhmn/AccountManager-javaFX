package id.dojo.accountmanagerjavafx.models;

import java.io.Serializable;
import java.util.HashMap;

public class AccountRead implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountName;
    private String signinUrl;
    private String username;
    private String password;
    private HashMap<String, String> passwordMap;

    public AccountRead(){}

    public AccountRead(String accountName, String signinUrl, String username, String password){
        this.accountName = accountName;
        this.signinUrl = signinUrl;
        this.username = username;
        this.password = password;
    }

    public AccountRead(String accountName, String signinUrl, String username, String password, HashMap<String, String> passwordMap){
        this.accountName = accountName;
        this.signinUrl = signinUrl;
        this.username = username;
        this.password = password;
        this.passwordMap = passwordMap;
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

    public HashMap<String, String> getPasswordMap() {
        return passwordMap;
    }

    public void setPasswordMap(HashMap<String, String> passwordMap) {
        this.passwordMap = passwordMap;
    }

}
