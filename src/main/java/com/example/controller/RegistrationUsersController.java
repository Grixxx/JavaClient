package com.example.controller;

import java.io.IOException;

import ConnectToServer.Client;
import ConnectToServer.InteractionsWithServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class RegistrationUsersController {


    @FXML
    private Button addAutorizButton;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpName;

    @FXML
    private PasswordField signUpPassowrd;


    @FXML
    private Button signUpRegistButton;

    private Stage stage;

    private Scene scene;

    private Parent root;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    @FXML
    void initialize() {
    }

    public void buttonRegistrat(ActionEvent event)  throws IOException {

        if (signUpName.getLength() < 1 || signUpName.getLength() > 20 && signUpLastName.getLength() < 1 || signUpLastName.getLength() > 20
                && signUpLogin.getLength() < 1 || signUpLogin.getLength() > 20 && signUpPassowrd.getLength() < 1 || signUpPassowrd.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            signUpName.clear();
            signUpLastName.clear();
            signUpLogin.clear();
            signUpPassowrd.clear();
        }
        else{
            Client.interactionsWithServer.registUsers(signUpName.getText(),  signUpLastName.getText(),
                    signUpLogin.getText(),signUpPassowrd.getText());
            alert.setTitle("Удачно");
            alert.setHeaderText(null);
            alert.setContentText("Вы успешно зарегистрировались");
            alert.showAndWait();
            Stage stage = (Stage) addAutorizButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }

    }
    public void windowAuthorization(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
