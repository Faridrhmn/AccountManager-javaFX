package id.dojo.accountmanagerjavafx;

import id.dojo.accountmanagerjavafx.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditController {

    @FXML
    private TextField name;
    @FXML
    private TextField url;
    @FXML
    private TextField username;
    @FXML
    private Label password;

    private Stage dialogStage;
    private HomeController homeController;
    private Account account;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleOk() {
        if(isInputValid()){
            account.setAccountName(name.getText());
            account.setSigninUrl(url.getText());
            account.setUsername(username.getText());
            account.setPassword(password.getText());
            homeController.updateAccount(account);
            dialogStage.close();
        }
    }

    public void setAccount(Account account) {
        this.account = account;
        name.setText(account.getAccountName());
        url.setText(account.getSiginUrl());
        username.setText(account.getUsername());
        password.setText(account.getPassword());
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
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleChangePass() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("ChangePass.fxml"));
            Parent parent = fxmlLoader.load();
            ChangePassController changePassControllerController = fxmlLoader.getController();
            changePassControllerController.setEditController(this);
            changePassControllerController.setAccount(account);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            changePassControllerController.setDialogStage(stage);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private void handleHistory() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("History-view.fxml"));
            Parent parent = fxmlLoader.load();
            HistoryController historyController = fxmlLoader.getController();
            historyController.setEditController(this);
            historyController.setAccount(account);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            historyController.setDialogStage(stage);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void updatePassword(String Password){
        password.setText(Password);
    }
}
