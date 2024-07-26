package id.dojo.accountmanagerjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField key;
    @FXML
    private TextField plain;

    public static String enkripVignere(String plain, String key){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char chiper[] = new char[plain.length()];
        for(int i =0; i<plain.length(); i++){
            char newKey = key.charAt((i % key.length()));
            char newPlain = alpha.charAt((alpha.indexOf(newKey)+alpha.indexOf(plain.charAt(i)))%alpha.length());
            chiper[i] += newPlain;
        }
        return new String(chiper);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if(plain.getText()==null||plain.getText().length()==0||key.getText()==null||key.getText().length()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Input all field");
            alert.showAndWait();
        }else{
            String chiper = enkripVignere(plain.getText(), key.getText());
            if(chiper.equals("mhlhhli")){
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("Home-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong input");
                alert.setHeaderText("Please check your key and password");
                alert.setContentText("Key or password wrong!");
                alert.showAndWait();
            }
        }
    }
}
