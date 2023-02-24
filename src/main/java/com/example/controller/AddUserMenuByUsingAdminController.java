package com.example.controller;

import ConnectToServer.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddUserMenuByUsingAdminController {

    @FXML
    private Button addUserByHelpAdminButton;

    @FXML
    private TextField addUserFname;

    @FXML
    private TextField addUserLname;

    @FXML
    private TextField addUserLogin;

    @FXML
    private PasswordField addUserPassword;
    private Stage stage;

    private Scene scene;

    private Parent root;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alertWar = new Alert(Alert.AlertType.WARNING);

    public void addUserByHelpAdmin(ActionEvent event) throws IOException {

        if (addUserFname.getLength() < 1 || addUserFname.getLength() > 20 || addUserLname.getLength() < 1 || addUserLname.getLength() > 20
                && addUserLogin.getLength() < 1 || addUserLogin.getLength() > 20 || addUserPassword.getLength() < 1 || addUserPassword.getLength() > 20) {
            alertWar.setTitle("Ошибка!");
            alertWar.setHeaderText(null);
            alertWar.setContentText("Введите во все строки ваши значения");
            alertWar.showAndWait();
            addUserFname.clear();
            addUserLname.clear();
            addUserLogin.clear();
            addUserPassword.clear();
        }
        else{
            Client.interactionsWithServer.addUser(addUserFname.getText(),  addUserLname.getText(),
                    addUserLogin.getText(),addUserPassword.getText());
            alert.setTitle("Удачно");
            alert.setHeaderText(null);
            alert.setContentText("Вы успешно добавили пользователя(рабочего)");
            alert.showAndWait();
            root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        }

    }
}

