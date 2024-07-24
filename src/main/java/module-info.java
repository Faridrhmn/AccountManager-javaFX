module id.dojo.accountmanagerjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens id.dojo.accountmanagerjavafx to javafx.fxml;
    exports id.dojo.accountmanagerjavafx;
}