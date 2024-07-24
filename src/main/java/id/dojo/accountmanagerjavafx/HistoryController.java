package id.dojo.accountmanagerjavafx;

import id.dojo.accountmanagerjavafx.models.Account;
import id.dojo.accountmanagerjavafx.models.PasswordMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.util.HashMap;

public class HistoryController {
    @FXML
    private TableView<PasswordMap> passwordTable;
    @FXML
    private TableColumn<PasswordMap, String> timeChange;
    @FXML
    private TableColumn<PasswordMap, String> password;

    EditController editController;
    private Stage dialogStage;
    Account account;

    @FXML
    private void initialize() {
    }


    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAccount(Account account) {
        this.account = account;
        HashMap<String, String> passwordMap = account.getPasswordMap();
        ObservableList<PasswordMap> observablePassword = FXCollections.observableArrayList();

        for (String i : passwordMap.keySet()) {
            observablePassword.add(new PasswordMap(i, passwordMap.get(i)));
        }

        // Set up the columns in the table
        timeChange.setCellValueFactory(cellData -> cellData.getValue().modificationTimeProperty());
        password.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        // Add observable list data to the table
        passwordTable.setItems(observablePassword);
    }

    @FXML
    private void handleOk() {
        dialogStage.close();
    }

}
