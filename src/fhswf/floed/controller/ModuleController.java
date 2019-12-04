package fhswf.floed.controller;

import fhswf.floed.custom.fields.FloatField;
import fhswf.floed.custom.fields.IntegerField;
import fhswf.floed.jpa.Module;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.window.WindowSizeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModuleController {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button backButton;

    @FXML
    public TextField nameField;
    @FXML
    public IntegerField maxCreditsField;
    @FXML
    public IntegerField weekhoursField;
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

    EntityManager entityManager;

    Module module;
    Map<String, String> errors;

    public ModuleController() {
        errors = new HashMap<>();
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(WindowSizeManager.getHeight());
        anchorPane.setPrefWidth(WindowSizeManager.getWidth());

        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();
    }

    public ModuleController(int id) {
        // TODO figure out how to pass data between controllers
    }

    /**
     * @throws IOException
     */
    @FXML
    public void backToMainScene() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/mainScene.fxml"));
        stage.setScene(new Scene(root));
    }

    /**
     *
     */
    @FXML
    public void createModule() {

    }

    /**
     *
     */
    private void fillModuleObject() {
        module = new Module();

        String moduleName = nameField.getText();
        if (moduleName != null && !moduleName.isEmpty()) {
            module.setName(moduleName);
        } else {
            errors.put("name", "Modulname nicht gefüllt.");
        }

        String creditpointsText = maxCreditsField.getText();
        if (creditpointsText != null && !creditpointsText.isEmpty()) {
            int creditpoints = maxCreditsField.getIntegerValue();
            if (creditpoints > 0) {
                module.setCreditpoints(creditpoints);
            } else {
                errors.put("creditpoints", "Creditspoints müssen größer als 0 sein.");
            }
        } else {
            errors.put("creditpoints", "Creditspoints nicht gefüllt.");
        }

        String weekhoursText = weekhoursField.getText();
        if (weekhoursText != null && !weekhoursText.isEmpty()) {
            int weekhours = weekhoursField.getIntegerValue();
            if (weekhours > 0) {
                module.setWeekhours(weekhours);
            } else {
                errors.put("weekhours", "Semesterwochenstunden müssen größer als 0 sein.");
            }
        } else {
            errors.put("weekhours", "Semesterwochenstunden nicht gefüllt.");
        }


    }
}
