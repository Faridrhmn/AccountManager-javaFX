package id.dojo.accountmanagerjavafx;

import id.dojo.accountmanagerjavafx.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddController {
    @FXML
    private TextField name;
    @FXML
    private TextField url;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    private Stage dialogStage;
    private HomeController homeController;
    private Account account;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            HashMap<String, String> passwordMap = new HashMap<>();
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date waktu = new Date();
            String waktuUbah = df.format(waktu);
            passwordMap.put(waktuUbah, password.getText());
            Account account = new Account(name.getText(), url.getText(), username.getText(), password.getText(), passwordMap);
            homeController.addAccount(account);
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (url.getText() == null || url.getText().length() == 0) {
            errorMessage += "No valid url!\n";
        }
        if (username.getText() == null || username.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
