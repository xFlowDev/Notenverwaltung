package fhswf.floed;

import fhswf.floed.window.WindowSizeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        double width = 1200;
        double height = 800;
        WindowSizeManager.setWidth(width);
        WindowSizeManager.setHeight(height);

        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainScene.fxml"));
        primaryStage.setTitle("Notenverwaltung");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
