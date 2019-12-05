package fhswf.floed.controller;

import fhswf.floed.window.WindowSizeManager;
import javafx.event.ActionEvent;
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
    @FXML
    public Button createModuleButton;
    @FXML
    public Button createLecturerButton;
    @FXML
    public Button createCourseButton;
    @FXML
    public Button createTypeButton;

    public MainController() {
        // Set the anchorPane to the specified size, so that all scenes have the same height when I open another
        anchorPane = new AnchorPane();
        anchorPane.setMinHeight(WindowSizeManager.getHeight());
        anchorPane.setMinWidth(WindowSizeManager.getWidth());


    }

    @FXML
    private void addModule() throws IOException {
        loadScene("../fxml/addModuleScene.fxml");
    }

    @FXML
    public void createModule(ActionEvent actionEvent) throws IOException {
        loadScene("../fxml/createModuleScene.fxml");
    }


    @FXML
    public void createLecturer(ActionEvent actionEvent) throws IOException {
        loadScene("../fxml/createLecturerScene.fxml");
    }

    @FXML
    public void createCourse(ActionEvent actionEvent) throws IOException {
        loadScene("../fxml/createCourseScene.fxml");
    }

    @FXML
    public void createType(ActionEvent actionEvent) throws IOException {
        loadScene("../fxml/createTypeScene.fxml");
    }

    private void loadScene(String target) throws IOException {
        WindowSizeManager.setHeight(anchorPane.getHeight());
        WindowSizeManager.setWidth(anchorPane.getWidth());


        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(target));
        stage.setScene(new Scene(root));
    }
}
