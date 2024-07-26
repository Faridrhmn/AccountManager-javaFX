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

    public AccountRead(String accountName, String signinUrl, String username, String password, HashMap<String, String> passwordMap){
        this.accountName = enkripVignere(accountName, "udin");
        this.signinUrl = enkripVignere(signinUrl, "udin");
        this.username = enkripVignere(username, "udin");
        this.password = enkripVignere(password, "udin");
        this.passwordMap = passwordMap;
    }

    public String getAccountName() {
        return dekripVignere(accountName, "udin");
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUsername() {
        return dekripVignere(username, "udin");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSigninUrl() {
        return dekripVignere(signinUrl, "udin");
    }

    public void setSigninUrl(String signinUrl) {
        this.signinUrl = signinUrl;
    }

    public String getPassword() {
        return dekripVignere(password, "udin");
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

    public static String enkripVignere(String plain, String key) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char chiper[] = new char[plain.length()];
        int keyIndex = 0;

        for (int i = 0; i < plain.length(); i++) {
            char plainChar = plain.charAt(i);
            if (Character.isLetter(plainChar)) {
                char newKey = key.charAt(keyIndex % key.length());
                char newPlain = alpha.charAt((alpha.indexOf(newKey) + alpha.indexOf(Character.toLowerCase(plainChar))) % alpha.length());
                if (Character.isUpperCase(plainChar)) {
                    newPlain = Character.toUpperCase(newPlain);
                }
                chiper[i] = newPlain;
                keyIndex++;
            } else {
                chiper[i] = plainChar; // keep spaces and other characters unchanged
            }
        }
        return new String(chiper);
    }

    public static String dekripVignere(String ciphertext, String key) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char plain[] = new char[ciphertext.length()];
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char charCipher = ciphertext.charAt(i);
            if (Character.isLetter(charCipher)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int newCharIndex = (alpha.indexOf(Character.toLowerCase(charCipher)) - alpha.indexOf(keyChar) + alpha.length()) % alpha.length();
                char newChar = alpha.charAt(newCharIndex);
                if (Character.isUpperCase(charCipher)) {
                    newChar = Character.toUpperCase(newChar);
                }
                plain[i] = newChar;
                keyIndex++;
            } else {
                plain[i] = charCipher; // keep spaces and other characters unchanged
            }
        }
        return new String(plain);
    }
}
