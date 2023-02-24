package com.example.controller;

import ConnectToServer.Client;
import Models.NowUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowUserNowInfoController {

    @FXML
    private TextField oneTextField;
    @FXML
    private TableColumn<NowUser, String> firstNameColumn;

    @FXML
    private TableColumn<NowUser, String> lastNameColumn;

    @FXML
    private TableColumn<NowUser, String> loginColumn;

    @FXML
    private TableColumn<NowUser, String> passwordColumn;

    @FXML
    private TableView<NowUser> tableView;
    @FXML
    private ObservableList<NowUser> userNowDataSource = FXCollections.observableArrayList();

    private Stage stage;

    private Scene scene;

    private Parent root;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alertWar = new Alert(Alert.AlertType.WARNING);

    @FXML
    void initialize() {
        initTable();
    }
    public void initTable() {
        userNowDataSource.clear();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        try {
            userNowDataSource.addAll(Client.interactionsWithServer.showNowUsers());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tableView.setItems(userNowDataSource);
    }

    public void deleteUser(ActionEvent event) {
        int  count = tableView.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.deleteUserinUserPanel(userNowDataSource.get(count).getIdUser());
        initTable();
    }
    public void changePassword(ActionEvent event) throws IOException {
        if (oneTextField.getLength() < 1 || oneTextField.getLength() > 20) {
            alertWar.setTitle("Ошибка!");
            alertWar.setHeaderText(null);
            alertWar.setContentText("Введите во все строки ваши значения");
            alertWar.showAndWait();
            oneTextField.clear();

        }else{
            Client.interactionsWithServer.changePassword(oneTextField.getText());
            alert.setTitle("Удачно");
            alert.setHeaderText(null);
            alert.setContentText("Вы успешно изменили пароль");
            alert.showAndWait();
            oneTextField.clear();
            initTable();
        }
    }
    public void backUserPanel(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
