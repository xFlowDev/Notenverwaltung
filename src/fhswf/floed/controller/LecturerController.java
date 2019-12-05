package fhswf.floed.controller;

import fhswf.floed.jpa.Lecturer;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.window.WindowSizeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LecturerController {

    @FXML
    AnchorPane anchorPane;


    @FXML
    public TextField firstnameField;
    @FXML
    public TextField lastnameField;
    @FXML
    public TextField titleField;


    @FXML
    public Button createLecturerButton;
    @FXML
    public Button backToMainButton;


    EntityManager entityManager;
    Lecturer lecturer;

    Map<String, String> errors;

    public LecturerController() {
        anchorPane = new AnchorPane();
        anchorPane.setMinHeight(WindowSizeManager.getHeight());
        anchorPane.setMinWidth(WindowSizeManager.getWidth());

        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();

        errors = new HashMap<>();
    }

    @FXML
    public void createLecturer() {
        fillLecturerObject();
        if (errors.size() > 0) {
            for (String key : errors.keySet()) {
                System.out.println(key + ": " + errors.get(key));
            }
        } else {
            entityManager.getTransaction().begin();
            entityManager.persist(lecturer);
            entityManager.getTransaction().commit();
            lecturer = null;
            System.out.println("saved new lecturer");
        }
    }


    private void fillLecturerObject() {
        lecturer = new Lecturer();

        String firstname = this.firstnameField.getText();
        if (firstname != null && !firstname.isEmpty()) {
            lecturer.setFirstname(firstname);
        } else {
            errors.put("firstname", "Vorname muss gefüllt sein");
        }

        String lastname = this.lastnameField.getText();
        if (lastname != null && !lastname.isEmpty()) {
            lecturer.setLastname(lastname);
        } else {
            errors.put("lastname", "Nachname muss gefüllt sein");
        }

        String title = this.titleField.getText();
        if (title != null && !title.isEmpty()) {
            lecturer.setTitle(title);
        } else {
            errors.put("title", "Titel muss gefüllt sein");
        }

    }

    @FXML
    public void backToMain() throws IOException {
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
