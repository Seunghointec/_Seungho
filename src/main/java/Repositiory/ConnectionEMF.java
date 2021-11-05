package Repositiory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionEMF {

    public static EntityManagerFactory getEMF() {
        return Persistence.createEntityManagerFactory("datasource");
    }
}
