package com.example.controller;

import ConnectToServer.Client;
import Models.NowUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserPanelController {

    @FXML
    private Button exitButton;

    @FXML
    private Button infoUserButton;

    @FXML
    private Button salaryEntryButton;
    @FXML
    private Label specialtieNameLable;
    private Stage stage;

    private Scene scene;

    private Parent root;
    private Label label;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    @FXML
    void initialize() {
        try {
            infoNowEmployee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void infoNowEmployee() throws IOException {
        Client.interactionsWithServer.checkNowUserIsEmployee();
        specialtieNameLable.setText(NowUser.getInstance().getSpecialtieName());
    }

    public void infoUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowUserNowInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public void showSpecialtie(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShowSpecialtietoUser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
  }
  public void showResultSalary(ActionEvent event) throws IOException{
        Client.interactionsWithServer.showSalaryNowEmployee();
        if(NowUser.getInstance().getResultSalary().equals("1")){
            alert.setTitle("Ну удачно");
            alert.setHeaderText(null);
            alert.setContentText("Ваша ЗП еще не подсчитана");
            alert.showAndWait();
        } else if (NowUser.getInstance().getResultSalary().equals("0")) {
            alert.setTitle("Не удачно");
            alert.setHeaderText(null);
            alert.setContentText("Устройтесь на работу");
            alert.showAndWait();
        }else {
            alert.setTitle("Удачно");
            alert.setHeaderText(null);
            alert.setContentText(NowUser.getInstance().getResultSalary());
            alert.showAndWait();
        }
    }
    public void deleteEmployee(ActionEvent event) throws IOException {
        Client.interactionsWithServer.deleteNowUserEmployee(NowUser.getInstance().getIdEmployee());
        infoNowEmployee();
    }
    public void backAutorPanel(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

