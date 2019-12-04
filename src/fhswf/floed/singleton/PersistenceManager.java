package fhswf.floed.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

    public static final boolean DEBUG = true;

    private static EntityManagerFactory singleton = null;

    public static EntityManagerFactory getInstance() {
        if (singleton == null)
            singleton = Persistence.createEntityManagerFactory("notenverwaltung");
        return singleton;
    }

    public void closeEntityManagerFactory() {
        if (singleton != null) {
            singleton.close();
            singleton = null;
            if (DEBUG)
                System.out.println("n*** Persistence finished at " + new java.util.Date());
        }
    }

    protected static void createEntityManagerFactory(EntityManagerFactory emf) {
        if (DEBUG)
            System.out.println("n*** Persistence started at " + new java.util.Date());
    }
}