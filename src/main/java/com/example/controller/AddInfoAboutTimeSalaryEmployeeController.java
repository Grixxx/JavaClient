package com.example.controller;

import ConnectToServer.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddInfoAboutTimeSalaryEmployeeController {


    @FXML
    private TextField oneTextField;
    @FXML
    private TextField twoTextFild;
    @FXML
    private Button calculateSalary;
    private Stage stage;

    private Scene scene;

    private Parent root;

Alert alert = new Alert(Alert.AlertType.INFORMATION);
Alert alertWar = new Alert(Alert.AlertType.WARNING);



    public void calculateSalary(ActionEvent event) throws IOException {
        if (oneTextField.getLength() < 1 || oneTextField.getLength() > 20 || twoTextFild.getLength() < 1 || twoTextFild.getLength() > 20) {
            alertWar.setTitle("Ошибка!");
            alertWar.setHeaderText(null);
            alertWar.setContentText("Введите во все строки ваши значения");
            alertWar.showAndWait();
            oneTextField.clear();
            twoTextFild.clear();

        }else{
            Client.interactionsWithServer.calculateTimeSalaryEmployee(oneTextField.getText(), twoTextFild.getText());
            alert.setTitle("Удачно");
            alert.setHeaderText(null);
            alert.setContentText("Вы успешно зарегистрировались");
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
