package com.example.controller;

import ConnectToServer.Client;
import ConnectToServer.InteractionsWithServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;

import static ConnectToServer.InteractionsWithServer.logout;


public class ClientApplication extends Application {
    private static InteractionsWithServer client;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("Authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Курсач");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                logout(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static void main(String[] args) {
        new Client().connectToServer();
        launch();
    }

}