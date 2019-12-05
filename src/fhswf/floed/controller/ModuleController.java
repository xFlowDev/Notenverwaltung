package fhswf.floed.controller;

import fhswf.floed.custom.fields.FloatField;
import fhswf.floed.custom.fields.IntegerField;
import fhswf.floed.jpa.Lecturer;
import fhswf.floed.jpa.Module;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.utils.MapHelper;
import fhswf.floed.window.WindowSizeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ModuleController implements Initializable {

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button backButton;
    @FXML
    public Button createModuleButton;

    @FXML
    public TextField nameField;
    @FXML
    public IntegerField maxCreditsField;
    @FXML
    public IntegerField weekhoursField;
    @FXML
    public IntegerField currentTryField;
    @FXML
    public ChoiceBox<String> lecturerField;
    @FXML
    public FloatField gradeField;
    @FXML
    public IntegerField creditsField;
    @FXML
    public TextField typeField;

    private EntityManager entityManager;

    private List<Lecturer> lecturers;
    private Map<Integer, String> lecturerNames;
    private List<String> lecturerFieldOptions;

    private Module module;

    private Map<String, String> errors;

    public ModuleController() {
        anchorPane = new AnchorPane();
        anchorPane.setMinHeight(WindowSizeManager.getHeight());
        anchorPane.setMinWidth(WindowSizeManager.getWidth());

        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();

        errors = new HashMap<>();

    }

    public ModuleController(int id) {
        // TODO figure out how to pass data between controllers
    }

    @FXML
    public void backToMain() throws IOException {
        loadScene("../fxml/mainScene.fxml");
    }

    @FXML
    public void createModule() {
        fillModule();
        if (errors.size() > 0) {
            for (String key : errors.keySet()) {
                System.out.println(key + ": " + errors.get(key));
            }
        } else {
            entityManager.getTransaction().begin();
            entityManager.persist(module);
            entityManager.getTransaction().commit();
            module = null;
            System.out.println("saved new module");
        }
    }

    private void fillModule() {
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

        String lecturerText = lecturerField.getValue();
        if (lecturerText != null && !lecturerText.isEmpty()) {
            Lecturer lecturer = null;
            Integer lecturerId;
            lecturerId = MapHelper.getKeyByValue(lecturerNames, lecturerText);
            if (lecturerId != null && lecturerId != 0) {
                lecturer = entityManager.find(Lecturer.class, lecturerId);
            } else {
                errors.put("lecturer", "Dozent konnte nicht gefunden werden.");
            }

            if (lecturer != null) {
                module.setLecturer(lecturer);
            } else {
                errors.put("lecturer", "Dozent konnte nicht gefunden werden.");
            }

        } else {
            errors.put("lecturer", "Dozent nicht gefüllt.");
        }
    }

    private void loadScene(String target) throws IOException {
        WindowSizeManager.setHeight(anchorPane.getHeight());
        WindowSizeManager.setWidth(anchorPane.getWidth());

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(target));
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLecturerNames();
    }

    public void setLecturerNames() {
        TypedQuery<Lecturer> lecturersQuery = entityManager.createNamedQuery("Lecturer.all", Lecturer.class);
        lecturers = lecturersQuery.getResultList();
        if (lecturers.size() <= 0) {
            System.out.println("No lecturers");
        } else {
            lecturerNames = new HashMap<>();
            lecturerFieldOptions = new ArrayList<>();
            String fullName;
            for (Lecturer l : lecturers) {
                fullName = l.getTitle() + " " + l.getFirstname() + " " + l.getLastname();
                lecturerNames.put(l.getId(), fullName);
                lecturerFieldOptions.add(fullName);
            }
            lecturerField.getItems().addAll(lecturerFieldOptions);
        }
    }
}
