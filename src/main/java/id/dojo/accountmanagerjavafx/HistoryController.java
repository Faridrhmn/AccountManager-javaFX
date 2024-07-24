package id.dojo.accountmanagerjavafx;

import javafx.stage.Stage;

public class HistoryController {

    EditController editController;
    private Stage dialogStage;

    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
