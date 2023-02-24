package com.example.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ConnectToServer.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class AutorizationController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSingButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private RadioButton roleSelectionButton;


    @FXML
    private PasswordField passwordField;
    private Stage stage;

    private Scene scene;

    private Parent root;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void windowRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonAuthorization(ActionEvent event){

        if (loginField.getLength() < 1 || loginField.getLength() > 20
                || passwordField.getLength() < 1 || passwordField.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            loginField.clear();
            passwordField.clear();
        }
        else{
//                try {
//                    if(Client.interactionsWithServer.authUser(loginField.getText(),passwordField.getText())=="trueAdmin"){
//                        Stage stage = (Stage) authSingButton.getScene().getWindow();
//                        alert.setTitle("Успешно!");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Вы вошли в качестве администратора");
//                        alert.showAndWait();
//                        root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
//                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//                        scene = new Scene(root);
//                        stage.setResizable(false);
//                        stage.setScene(scene);
//                        stage.show();
//
//                    }
//                    else{
//                        alert.setTitle("Ошибка!");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Неверный логин или пароль");
//                        alert.showAndWait();
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                try {
                    if(Client.interactionsWithServer.authUser(loginField.getText(),passwordField.getText()).equals("trueUser")){
                        alert.setTitle("Успешно!");
                        alert.setHeaderText(null);
                        alert.setContentText("Вы вошли в качестве работника");
                        alert.showAndWait();
                        root = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();

                    } else if (Client.interactionsWithServer.authUser(loginField.getText(),passwordField.getText()).equals("trueAdmin")) {
                        Stage stage = (Stage) authSingButton.getScene().getWindow();
                        alert.setTitle("Успешно!");
                        alert.setHeaderText(null);
                        alert.setContentText("Вы вошли в качестве администратора");
                        alert.showAndWait();
                        root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } else{
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Неверный логин или пароль");
                        alert.showAndWait();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

}