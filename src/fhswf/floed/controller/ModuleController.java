package fhswf.floed.controller;

import fhswf.floed.custom.fields.FloatField;
import fhswf.floed.custom.fields.IntegerField;
import fhswf.floed.window.WindowSizeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ModuleController {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button backButton;
    @FXML
    public TextField moduleField;
    @FXML
    public IntegerField maxCreditsField;
    @FXML
    public IntegerField semesterhoursField;
    @FXML
    public IntegerField currentTryField;
    @FXML
    public TextField lecturerField;
    @FXML
    public FloatField gradeField;
    @FXML
    public IntegerField creditsField;
    @FXML
    public TextField typeField;

    public ModuleController() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(WindowSizeManager.getHeight());
        anchorPane.setPrefWidth(WindowSizeManager.getWidth());

    }

    @FXML
    public void backToMainScene() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/mainScene.fxml"));
        stage.setScene(new Scene(root));
    }
}
