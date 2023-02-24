module com.example.kpclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.example.controller to javafx.fxml;
    opens Models to java.base;
    exports Models;
    exports com.example.controller;
}