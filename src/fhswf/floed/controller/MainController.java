package fhswf.floed.controller;

import fhswf.floed.window.WindowSizeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button addModuleButton;

    public MainController() {
        // Set the anchorPane to the specified size, so that all scenes have the same height when I open another
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(WindowSizeManager.getHeight());
        anchorPane.setPrefWidth(WindowSizeManager.getWidth());
        
        
    }

    @FXML
    private void addModule() throws IOException {
        WindowSizeManager.setHeight(anchorPane.getHeight());
        WindowSizeManager.setWidth(anchorPane.getWidth());

        Stage stage = (Stage) addModuleButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/addModuleScene.fxml"));
        stage.setScene(new Scene(root));
    }
}
