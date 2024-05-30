package com.targetindia.targetready.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private JpaUtil(){}
    private static final EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("Shippment-Message-DB" + "");
    }
    public static EntityManager createEntityManager(){
        return emf.createEntityManager();
    }
}
