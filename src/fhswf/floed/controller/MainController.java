package fhswf.floed.controller;

import fhswf.floed.ModuleGradeTableModel;
import fhswf.floed.jpa.Modulegrade;
import fhswf.floed.jpa.User;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.window.WindowSizeManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button addModuleButton;
    @FXML
    public Button editGradeButton;
    @FXML
    public Button createModuleButton;
    @FXML
    public Button createLecturerButton;
    @FXML
    public Button createCourseButton;
    @FXML
    public Button createTypeButton;

    @FXML
    public TableView<ModuleGradeTableModel> gradeTable;
    @FXML
    public TableColumn<ModuleGradeTableModel, Integer> idColumn;
    @FXML
    public TableColumn<ModuleGradeTableModel, String> moduleNameColumn;
    @FXML
    public TableColumn<ModuleGradeTableModel, Float> gradeColumn;
    @FXML
    public TableColumn<ModuleGradeTableModel, Integer> creditColumn;
    @FXML
    public TableColumn<ModuleGradeTableModel, Integer> tryColumn;

    private EntityManager entityManager;
    private User currentUser;
    private Modulegrade selectedGrade;

    public MainController() {
        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the anchorPane to the specified size, so that all scenes have the same height when I open another
        anchorPane.setMinHeight(WindowSizeManager.getHeight());
        anchorPane.setMinWidth(WindowSizeManager.getWidth());

        currentUser = entityManager.find(User.class, 1);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        moduleNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<>("creditpoints"));
        tryColumn.setCellValueFactory(new PropertyValueFactory<>("gradetry"));

        gradeTable.getSelectionModel().selectedItemProperty().addListener(this::setSelectedGrade);

        fillTable();
    }

    private void setSelectedGrade(Observable observable) {
        ModuleGradeTableModel selectedGrade = gradeTable.getSelectionModel().getSelectedItem();
        this.selectedGrade = new Modulegrade();
        this.selectedGrade = entityManager.find(Modulegrade.class, selectedGrade.getId());
    }

    @FXML
    public void editGrade() throws IOException {
        if (selectedGrade != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/addModuleScene.fxml"));
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            ModuleController moduleController = loader.getController();
            moduleController.editGrade(selectedGrade);
            stage.show();
        }
    }

    private void fillTable() {
        TypedQuery<Modulegrade> typedQuery = entityManager.createNamedQuery("Modulegrade.getAllFromUser", Modulegrade.class);
        typedQuery.setParameter("user", currentUser);
        List<ModuleGradeTableModel> grades = new ArrayList<>();
        for (Modulegrade grade : typedQuery.getResultList()) {
            grades.add(new ModuleGradeTableModel(grade));
        }

        if (!grades.isEmpty()) {
            ObservableList<ModuleGradeTableModel> modulegrades = FXCollections.observableArrayList(grades);
            gradeTable.setItems(modulegrades);
        }
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
