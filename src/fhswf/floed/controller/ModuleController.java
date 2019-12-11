package fhswf.floed.controller;

import fhswf.floed.custom.fields.FloatField;
import fhswf.floed.custom.fields.IntegerField;
import fhswf.floed.jpa.Lecturer;
import fhswf.floed.jpa.Module;
import fhswf.floed.jpa.ModuleType;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.utils.MapHelper;
import fhswf.floed.window.WindowSizeManager;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
    public ChoiceBox<String> moduleSelectField;
    @FXML
    public IntegerField maxCreditsField;
    @FXML
    public IntegerField weekhoursField;
    @FXML
    public IntegerField currentTryField;
    @FXML
    public ChoiceBox<String> lecturerField;
    @FXML
    public TextField lecturerDisplayField;
    @FXML
    public FloatField gradeField;
    @FXML
    public IntegerField creditsField;
    @FXML
    public IntegerField typeField;
    @FXML
    public ChoiceBox<String> moduleTypeSelectionField;

    private EntityManager entityManager;

    private Map<Integer, String> lecturerNames;
    private Map<Integer, String> moduleTypes;
    private Map<Integer, String> moduleNames;

    private Module module;

    private Map<String, String> errors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.setMinHeight(WindowSizeManager.getHeight());
        anchorPane.setMinWidth(WindowSizeManager.getWidth());

        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();

        setLecturerNames();
        setModuleTypes();
        setModuleNames();

        if (moduleSelectField != null) {
            moduleSelectField.getSelectionModel().selectedItemProperty().addListener(this::loadModuleData);
        }

        errors = new HashMap<>();
    }

    private void loadModuleData(Observable observable) {
        String selectedModule = moduleSelectField.getSelectionModel().getSelectedItem();
        if (selectedModule != null && !selectedModule.isEmpty()) {
            Integer moduleId = MapHelper.getKeyByValue(moduleNames, selectedModule);
            if (moduleId != null) {
                Module module = entityManager.find(Module.class, moduleId);

//                moduleSelectField.setDisable(true);
                maxCreditsField.setText(Integer.toString(module.getCreditpoints()));
                weekhoursField.setText(Integer.toString(module.getWeekhours()));
                Lecturer lecturer = module.getLecturer();
                lecturerDisplayField.setText(lecturer.fullName());
                ModuleType moduleType = module.getModuleType();
                typeField.setText(moduleType.getName());

                currentTryField.setIntegerValue(1);
                gradeField.setDisable(false);
                creditsField.setDisable(false);
                // TODO Once Type is ready to use, go on here
            }
        }
    }


    public ModuleController() {
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

        String moduleTypeText = moduleTypeSelectionField.getValue();
        if (moduleTypeText != null && !moduleTypeText.isEmpty()) {
            ModuleType moduleType = null;
            Integer moduleTypeId;
            moduleTypeId = MapHelper.getKeyByValue(moduleNames, moduleTypeText);
            if (moduleTypeId != null && moduleTypeId != 0) {
                moduleType = entityManager.find(ModuleType.class, moduleTypeId);
            } else {
                errors.put("module_type", "ModulTyp konnte nicht gefunden werden");
            }

            if (moduleType != null) {
                module.setModuleType(moduleType);
            } else {
                errors.put("module_type", "ModulTyp konnte nicht gefunden werden");
            }
        } else {
            errors.put("module_type", "Modultyp nicht gefüllt");
        }
    }

    private void loadScene(String target) throws IOException {
        WindowSizeManager.setHeight(anchorPane.getHeight());
        WindowSizeManager.setWidth(anchorPane.getWidth());

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(target));
        stage.setScene(new Scene(root));
    }

    private void setLecturerNames() {
        if (lecturerField != null) {
            TypedQuery<Lecturer> lecturersQuery = entityManager.createNamedQuery("Lecturer.all", Lecturer.class);
            List<Lecturer> lecturers = lecturersQuery.getResultList();
            if (lecturers.size() <= 0) {
                System.out.println("No lecturers");
            } else {
                lecturerNames = new HashMap<>();
                List<String> lecturerFieldOptions = new ArrayList<>();
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

    private void setModuleTypes() {
        if (moduleTypeSelectionField != null) {
            TypedQuery<ModuleType> typedQuery = entityManager.createNamedQuery("ModuleType.all", ModuleType.class);
            List<ModuleType> moduleTypes = typedQuery.getResultList();
            if (moduleTypes.size() <= 0) {
                System.out.println("No ModuleTypes");
            } else {
                this.moduleTypes = new HashMap<>();
                List<String> typeOptions = new ArrayList<>();
                for (ModuleType t : moduleTypes) {
                    this.moduleTypes.put(t.getId(), t.getName());
                    typeOptions.add(t.getName());
                }
                moduleTypeSelectionField.getItems().addAll(typeOptions);
            }
        }
    }

    public void setModuleNames() {
        if (moduleSelectField != null) {
            TypedQuery<Module> moduleTypedQuery = entityManager.createNamedQuery("Module.all", Module.class);
            List<Module> modules = moduleTypedQuery.getResultList();
            if (modules.size() <= 0) {
                System.out.println("No modules");
            } else {
                moduleNames = new HashMap<>();
                List<String> moduleFieldOptions = new ArrayList<>();
                for (Module m : modules) {
                    moduleNames.put(m.getId(), m.getName());
                    moduleFieldOptions.add(m.getName());
                }
                moduleSelectField.getItems().addAll(moduleFieldOptions);
            }
        }
    }


    @FXML
    public void backToMain() throws IOException {
        loadScene("../fxml/mainScene.fxml");
    }
}
