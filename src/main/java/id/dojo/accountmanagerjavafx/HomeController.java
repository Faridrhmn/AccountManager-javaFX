package id.dojo.accountmanagerjavafx;

import id.dojo.accountmanagerjavafx.models.Account;
import id.dojo.accountmanagerjavafx.models.AccountRead;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class HomeController {
    @FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, String> nameColumn;
    @FXML
    private TableColumn<Account, String> urlColumn;
    @FXML
    private TableColumn<Account, String> usernameColumn;
    @FXML
    private TableColumn<Account, String> passwordColumn;
    @FXML
    private TextField search;

    private ObservableList<Account> accountsData = FXCollections.observableArrayList();

    private HomeApplication mainApp;

    private String fileLocation;

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Author: Udin Sedunia\nInterested with my work?\nPlease contact: bfaridrahman@gmail.com");
        alert.showAndWait();
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(HomeApplication.getPrimaryStage());

        if (file != null) {
            System.out.println("File path: " + file.getPath());
            handleLoadPerson(file.getPath());
            setFileLocation(file.getPath());
        } else {
            System.out.println("gagal");
        }
    }

    @FXML
    private void handleNew() {
        accountsData.clear();
        accountTable.setItems(accountsData);
        setFileLocation("");
        System.out.println("masuk");
    }

    @FXML
    private void handleSearch(){
        String search = getSearch();
        ObservableList<Account> filteredAccounts = FXCollections.observableArrayList();
        System.out.println(search);

        for (Account account : accountsData) {
            if (account.getAccountName().equals(search)) {
                filteredAccounts.add(account);
            }
        }

        if (!filteredAccounts.isEmpty()) {
            accountTable.setItems(filteredAccounts);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Not Found!");
            alert.setHeaderText("Name not found");
            alert.setContentText("Please input another name");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleRefresh() {
        accountTable.setItems(accountsData);
    }

    @FXML
    private void handleAdd(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("Add-view.fxml"));
            Parent parent = fxmlLoader.load();
            AddController addController = fxmlLoader.getController();
            addController.setHomeController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            addController.setDialogStage(stage);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    private void handleEdit(){
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("Edit-view.fxml"));
                Parent parent = fxmlLoader.load();
                EditController editController = fxmlLoader.getController();
                editController.setHomeController(this);
                editController.setAccount(accountTable.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                editController.setDialogStage(stage);
                stage.show();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDelete() {
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            accountTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleChooseSave() {
        if(getFileLocation()==null||getFileLocation()==""){
            handleSaveAs();
        }else{
            handleSave();
        }
    }

    @FXML
    private void initialize() {
        accountTable.setItems(accountsData);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().accountNameProperty());
        urlColumn.setCellValueFactory(cellData -> cellData.getValue().signinProperty());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
    }

    public HomeController(){}

    public void handleLoadPerson(String location) {
        try (FileInputStream fileInputStream = new FileInputStream(location);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            List<AccountRead> accountList = (List<AccountRead>) objectInputStream.readObject();
            System.out.println(accountList);
            List<Account> accounts = accountList.stream()
                    .map(Account::readFileOpen)
                    .collect(Collectors.toList());
            accountTable.getItems().setAll(accounts);
            ObservableList<Account> observableAccounts = FXCollections.observableArrayList(accounts);
            accountTable.setItems(observableAccounts);
            System.out.println(observableAccounts);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + location, e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error while loading accounts", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

//    @FXML
    private void handleSave() {
//        String fileName = "/home/udin/IdeaProjects/save/account.txt";
        List<Account> accounts = accountTable.getItems();
        System.out.println("Number of account to save: " + accounts.size());

        if (accounts == null || accounts.isEmpty()) {
            throw new RuntimeException("No accounts to save");
        }

        List<AccountRead> personDataList = accounts.stream()
                .map(Account::toAccountDataWithTime)
                .collect(Collectors.toList());

        try (FileOutputStream fileOutputStream = new FileOutputStream(getFileLocation());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(personDataList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + getFileLocation(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving accounts", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

    public void addAccount(Account account) {
        accountsData.add(account);
        accountTable.setItems(accountsData); // This line may be optional if the TableView automatically updates.
    }

    public String getSearch() {
        return search.getText();
    }

    public void updateAccount(Account account) {
        accountTable.refresh();
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

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

    public static String dekripVignere(String ciphertext, String key) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int keyIndex = 0;
        char plain[] = new char[ciphertext.length()];
        for (int i = 0; i < ciphertext.length(); i++) {
            char charCipher = ciphertext.charAt(i);
            char keyChar = key.charAt(keyIndex % key.length());
            int newCharIndex = (alpha.indexOf(charCipher) - alpha.indexOf(keyChar) + alpha.length()) % alpha.length();
            char newChar = alpha.charAt(newCharIndex);
            plain[i] += newChar;
            keyIndex++;
        }
        return new String(plain);
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("newfile.txt");

        File file = fileChooser.showSaveDialog(HomeApplication.getPrimaryStage());

        if (file != null) {
            saveToFile(file);
            setFileLocation(file.getPath());
        }
    }

    private void saveToFile(File file) {
        List<Account> accounts = accountTable.getItems();
        System.out.println("Number of accounts to save: " + accounts.size());

        if (accounts == null || accounts.isEmpty()) {
            throw new RuntimeException("No accounts to save");
        }

        List<AccountRead> accountDataList = accounts.stream()
                .map(Account::toAccountDataWithTime)
                .collect(Collectors.toList());

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(accountDataList);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving accounts", e);
        }
    }

}
