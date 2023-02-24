package com.example.controller;

import ConnectToServer.Client;
import Models.Employee;
import Models.Specialtie;
import Models.TimeSalary;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AdminPanelController {

    @FXML
    private TableColumn<User, String> fNameUsersColumn;

    @FXML
    private TableColumn<User, Integer> idUsersColumn;

    @FXML
    private TableColumn<User, String> lNameUsersColumn;
    @FXML
    private TableColumn<User, String> loginUsersColumn;

    @FXML
    private TableColumn<User, String> passwordUsersColumn;
    @FXML
    private TableColumn<User, String> roleUserColumn;

    @FXML
    private TableColumn<Employee, Integer> idEmployeeColumn;
    @FXML
    private TableColumn<Employee, Integer> idUserFKemployeeColumn;
    @FXML
    private TableColumn<Employee, Integer> idSpecialtieFKemployeeColumn;
    @FXML
    private TableColumn<Employee,BigDecimal> resultSalaryColumn;
    @FXML
    private TableColumn<Employee,BigDecimal> monthlyTariffRateColumn;

    @FXML
    private TableColumn<Specialtie, Integer> idSpecialtieColumn;

    @FXML
    private TableColumn<Specialtie, String> specialtieNameColumn;
    @FXML
    private TextField addSpecialtieTextField;
    @FXML
    private TextField addIDSpecialtieWihtEmloyField;

    @FXML
    private TextField addIDemployeeInSpecialtieField;
    @FXML
    private TextField addIdEmployeeforUseSalary;




    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private TableView<Employee> tableViewEmployee;
    @FXML
    private TableView<Specialtie> tableViewSpecialtie;



    @FXML
    private Button updateButton;
    @FXML
    private Button addEmployeeButton;
    @FXML
    private Button addUserButton;

    @FXML
    private Button deleteUserButton;
    @FXML
    private Button countSelectedTimeSalaryButton;

    @FXML
    private Button addSpecialtieButton;
    @FXML
    private Button addSpecialtieToEmployeeButton;
    @FXML
    private Button delSpecialtieButton;
    @FXML
    private Button updateButton1;

    @FXML
    private Button updateButton2;



    @FXML
    private Button timeSalaryButton;
    @FXML
    private Button timeWithPremiumSalaryButton;
    @FXML
    private Button directPieceworkSalaryButton;
    @FXML
    private Button directWithPremiumPieceworkSalaryButton;
    @FXML
    private Button pieceProgressiveSalaryButton;
    @FXML
    private Button indirectPieceworkSalaryButton;



    private ObservableList<User> userNowDataSource = FXCollections.observableArrayList();
    private ObservableList<Employee> employeesDataSource= FXCollections.observableArrayList();
    private ObservableList<Specialtie> specialtieDataSource = FXCollections.observableArrayList();




    private Stage stage;

    private Scene scene;

    private Parent root;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alertW = new Alert(Alert.AlertType.WARNING);



    @FXML
    public void initialize() {
        initTableUser();
        initTableEmployee();
        updateButton.setOnAction(event -> {
            initTableUser();
        });
        updateButton1.setOnAction(event -> {
            initTableEmployee();
        });
        updateButton2.setOnAction(event -> {
            initTableSpecialtie();
        });
        initTableSpecialtie();
    }

    public void initTableUser() {
        userNowDataSource.clear();
        idUsersColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        fNameUsersColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lNameUsersColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        loginUsersColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
        passwordUsersColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        roleUserColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
        try {
            userNowDataSource.addAll(Client.interactionsWithServer.showAllUsers());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tableViewUsers.setItems(userNowDataSource);
    }
    public void initTableEmployee() {
        employeesDataSource.clear();
        idEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
        idUserFKemployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idUserFK"));
        idSpecialtieFKemployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idSpecialtieFK"));
        resultSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("ResultSalary"));
        monthlyTariffRateColumn.setCellValueFactory(new PropertyValueFactory<>("MonthlyTariffRate"));

        try {
            employeesDataSource.addAll(Client.interactionsWithServer.showAllEmployee());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tableViewEmployee.setItems(employeesDataSource);
    }
    public void initTableSpecialtie() {
        specialtieDataSource.clear();
        idSpecialtieColumn.setCellValueFactory(new PropertyValueFactory<>("idSpecialtie"));
        specialtieNameColumn.setCellValueFactory(new PropertyValueFactory<>("specialtieName"));

        try {
            specialtieDataSource.addAll(Client.interactionsWithServer.showAllSpecialtie());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tableViewSpecialtie.setItems(specialtieDataSource);
    }





   public void addUserInAdminTable(ActionEvent event)throws IOException{
    root = FXMLLoader.load(getClass().getResource("AddUserMenuByUsingAdmin.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
}
    public void addSelectedEmployeerAction(ActionEvent event) {
        int  count = tableViewUsers.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.addEmployee(userNowDataSource.get(count).getIdUser());
        initTableEmployee();
    }

    public void addSpecialtie(ActionEvent event) {

        if (addSpecialtieTextField.getLength() < 1 || addSpecialtieTextField.getLength() > 20) {
            alertW.setTitle("Ошибка!");
            alertW.setHeaderText(null);
            alertW.setContentText("Введите во все строки ваши значения");
            alertW.showAndWait();
            addSpecialtieTextField.clear();

        }
        else{
            Client.interactionsWithServer.addSpecialtie(addSpecialtieTextField.getText());
        }
        initTableSpecialtie();
    }
    public void addSpecialtieToEmployee(ActionEvent event) {
        if (addIDemployeeInSpecialtieField.getLength() < 1 || addIDemployeeInSpecialtieField.getLength() > 20
                || addIDSpecialtieWihtEmloyField.getLength() < 1 || addIDSpecialtieWihtEmloyField.getLength() > 20) {
            alertW.setTitle("Ошибка!");
            alertW.setHeaderText(null);
            alertW.setContentText("Введите во все строки ваши значения");
            alertW.showAndWait();
            addIDemployeeInSpecialtieField.clear();
            addIDSpecialtieWihtEmloyField.clear();

        }
        else{
            Client.interactionsWithServer.addSpecialtieToEmployee(addIDemployeeInSpecialtieField.getText(),addIDSpecialtieWihtEmloyField.getText());
        }
        initTableEmployee();
        initTableSpecialtie();
    }


    public void deleteSelectedUserAction(ActionEvent event) {
        int  count = tableViewUsers.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.deleteUser(userNowDataSource.get(count).getIdUser());
        initTableUser();
    }
    public void deleteEmployee(ActionEvent event) {
        int  count = tableViewEmployee.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.deleteEmployee(employeesDataSource.get(count).getIdEmployee());
        initTableEmployee();
    }
    public void deleteSpecialtie(ActionEvent event) {
        int  count = tableViewSpecialtie.getSelectionModel().getSelectedCells().get(0).getRow();
        Client.interactionsWithServer.deleteSpecialtie(specialtieDataSource.get(count).getIdSpecialtie());
        initTableEmployee();
        initTableSpecialtie();
    }



    public void AddInfoAboutTimeSalaryEmployee(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }
        else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddInfoAboutTimeSalaryEmployee.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void AddInfoAboutTimePremiumSalaryEmployee(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddInfoAboutTimePremiumSalaryEmployee.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfoStraightPieceworkSalary(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddStraightPieceworksalary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfoPremiumPieceworkSalary(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddPremiumPieceworksalary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfoProgressivePieceworkSalary(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddProgressivePieceworksalary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfoIndirectPieceworkSalary(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddIndirectPieceworksalary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfoAccordPieceworksalary(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddAccordPieceworksalary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfoAccordPremiumPieceworksalary(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddAccordPremiumPieceworksalary.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addInfomonthlyTariffRate(ActionEvent event) throws IOException{
        if (addIdEmployeeforUseSalary.getLength() < 1 || addIdEmployeeforUseSalary.getLength() > 20) {
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Введите во все строки ваши значения");
            alert.showAndWait();
            addIdEmployeeforUseSalary.clear();

        }else {
            Employee.getInstance().setIdEmployee(Integer.parseInt(addIdEmployeeforUseSalary.getText()));
            root = FXMLLoader.load(getClass().getResource("AddMonthlyTariffRate.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
}

