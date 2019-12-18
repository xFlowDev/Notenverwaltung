package fhswf.floed.controller;

import fhswf.floed.jpa.Module;
import fhswf.floed.jpa.Modulegrade;
import fhswf.floed.jpa.User;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.window.WindowSizeManager;
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
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public Button backButton;
    @FXML
    public TextField kolloqiumField;
    @FXML
    public TextField kolloqiumCreditsField;
    @FXML
    public TextField averageField;
    @FXML
    public TextField examField;
    @FXML
    public TextField examCreditsField;
    @FXML
    public TextField creditSumField;

    EntityManager entityManager;

    public OverviewController() {
        EntityManagerFactory factory = PersistenceManager.getInstance();
        entityManager = factory.createEntityManager();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User currentUser = entityManager.find(User.class, 1);
        TypedQuery<Modulegrade> query = entityManager.createNamedQuery("Modulegrade.getAllFromUser", Modulegrade.class).setParameter("user", currentUser);
        List<Modulegrade> grades = query.getResultList();
        List<Modulegrade> requiredModuleGrades = new ArrayList<>();

        Module module;
        for (Modulegrade modulegrade : grades) {
            module = modulegrade.getModule();
            if (module.getModuleType().getName().equals("Pflichtmodul")) {
                requiredModuleGrades.add(modulegrade);
            }
        }

        int count = requiredModuleGrades.size();
        if (count > 0) {
            int creditsum = 0;
            double average = 0;
            int credits = 0;
            double grade = 0;
            for (Modulegrade modulegrade : requiredModuleGrades) {
                credits = modulegrade.getCreditpoints();
                grade = modulegrade.getGrade();
                average += (grade * credits);
                creditsum += credits;
            }

            double weightedAverage = average / creditsum;
            DecimalFormat df = new DecimalFormat("#.00");
            averageField.setText(df.format(weightedAverage));
            creditSumField.setText(Integer.toString(creditsum));
            kolloqiumCreditsField.setText("3");
            examCreditsField.setText("12");

        } else {
            System.out.println("No grades tracked");
        }
    }


    private void loadScene(String target) throws IOException {
        WindowSizeManager.setHeight(anchorPane.getHeight());
        WindowSizeManager.setWidth(anchorPane.getWidth());

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(target));
        stage.setScene(new Scene(root));
    }

    @FXML
    public void backToMain() throws IOException {
        loadScene("../fxml/mainScene.fxml");
    }
}
