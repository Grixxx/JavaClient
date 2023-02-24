package com.example.controller;

import ConnectToServer.Client;
import Models.Specialtie;
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

public class ShowSpecialtietoUserController {
    @FXML
    private TextField oneTextField;
    @FXML
    private TableColumn<Specialtie, Integer> idSpecialtieColumn;

    @FXML
    private TableColumn<Specialtie, String> specialtieNameColumn;


    @FXML
    private TableView<Specialtie> tableView;
    @FXML
    private ObservableList<Specialtie> userNowSpecialtie = FXCollections.observableArrayList();

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
        userNowSpecialtie.clear();
        idSpecialtieColumn.setCellValueFactory(new PropertyValueFactory<>("idSpecialtie"));
        specialtieNameColumn.setCellValueFactory(new PropertyValueFactory<>("specialtieName"));
        try {
            userNowSpecialtie.addAll(Client.interactionsWithServer.showAllSpecialtieToUSer());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tableView.setItems(userNowSpecialtie);
    }

    public void addSpecialtieToEmployee(ActionEvent event)throws IOException {
        int  count = tableView.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.addSpecialtieToUser(userNowSpecialtie.get(count).getIdSpecialtie());
        initTable();
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
