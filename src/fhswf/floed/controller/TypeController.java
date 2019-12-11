package fhswf.floed.controller;

import fhswf.floed.jpa.ModuleType;
import fhswf.floed.jpa.Type;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.window.WindowSizeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TypeController implements Initializable {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button backButton;
    @FXML
    public Button createTypeButton;
    @FXML
    public TextField nameField;

    private EntityManager entityManager;

    private Map<String, String> errors;
    private ModuleType moduleType;

    public TypeController() {
        errors = new HashMap<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();
    }

    @FXML
    public void createType(ActionEvent actionEvent) throws IOException {
        fillType();
        if (errors.size() > 0) {
            for (String key : errors.keySet()) {
                System.out.println(key + ": " + errors.get(key));
            }
        } else {
            entityManager.getTransaction().begin();
            entityManager.persist(moduleType);
            entityManager.getTransaction().commit();
            System.out.println("saved new type");
            loadScene("../fxml/mainScene.fxml");
        }
    }

    private void fillType() {
        moduleType = new ModuleType();
        String name = nameField.getText();
        if (name != null && !name.isEmpty()) {
            moduleType.setName(name);
        } else {
            errors.put("name", "Name muss ausgefüllt sein.");
        }
    }

    @FXML
    public void backToMain(ActionEvent actionEvent) throws IOException {
        loadScene("../fxml/mainScene.fxml");
    }

    private void loadScene(String target) throws IOException {
        WindowSizeManager.setHeight(anchorPane.getHeight());
        WindowSizeManager.setWidth(anchorPane.getWidth());

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(target));
        stage.setScene(new Scene(root));
    }
}
