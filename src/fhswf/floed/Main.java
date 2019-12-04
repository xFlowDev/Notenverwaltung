package fhswf.floed;

import fhswf.floed.jpa.Type;
import fhswf.floed.jpa.User;
import fhswf.floed.singleton.PersistenceManager;
import fhswf.floed.window.WindowSizeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class Main extends Application {

    private void createAdminUserIfNotExists() {
        EntityManagerFactory factory = PersistenceManager.getInstance();
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Type> typeQuery = manager.createNamedQuery("Type.findByName", Type.class).setParameter("name", "Admin");
        Type type = null;
        try {
            type = typeQuery.getSingleResult();
        } catch (Exception e) {
            type = new Type();
            type.setName("Admin");
            manager.getTransaction().begin();
            manager.persist(type);
            manager.getTransaction().commit();
            type = typeQuery.getSingleResult();
        }

        TypedQuery<User> userQuery = manager.createNamedQuery("User.findByName", User.class).setParameter("name", "xflowdev");
        User user = null;
        try {
            user = userQuery.getSingleResult();
        } catch (Exception e) {
            user = new User();
            user.setFirstname("Florian");
            user.setLastname("Lödige");
            user.setEmail("xflowdev@protonmail.com");
            user.setUsername("xflowdev");
            // TODO Create a HashHelper which hashes the Password
            user.setPassword("password");
            user.setSalt("0123456789012345");
            user.setType(type);
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        double width = 1200;
        double height = 800;
        WindowSizeManager.setWidth(width);
        WindowSizeManager.setHeight(height);

        String target = "fxml/mainScene.fxml";


        this.createAdminUserIfNotExists();


        Parent root = FXMLLoader.load(getClass().getResource(target));
        primaryStage.setTitle("Notenverwaltung");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
