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

public class ChangePassController {

    @FXML
    private TextField pass;

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    Account account;

    EditController editController;
    private Stage dialogStage;

    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()){
            HashMap<String, String> passwordMap = account.getPasswordMap();
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date waktu = new Date();
            String waktuUbah = df.format(waktu);
            passwordMap.put(waktuUbah, pass.getText());
            account.setPasswordMap(passwordMap);
            account.setPassword(pass.getText());
            editController.updatePassword(pass.getText());
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (pass.getText() == null || pass.getText().length() == 0) {
            errorMessage += "No valid password!\n";
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
}
