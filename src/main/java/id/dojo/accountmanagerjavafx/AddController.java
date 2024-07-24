package id.dojo.accountmanagerjavafx;

import id.dojo.accountmanagerjavafx.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void setAccount(Account account) {
        this.account = account;
        name.setText(account.getAccountName());
        url.setText(account.getSiginUrl());
        username.setText(account.getUsername());
        password.setText(account.getPassword());
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Account account = new Account(name.getText(), url.getText(), username.getText(), password.getText());
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
