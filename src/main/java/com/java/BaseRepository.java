package com.java;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


public class BaseRepository {

    //@PersistenceContext --> this will do the job of the below  constructor
    EntityManager entityManager; //global

    public BaseRepository() { //even we dont need this ctor in spring, @PersistenceContext will take care of it
        System.out.println("BaseRepository()....");
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        //set the global ref
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

//    @Component, @Repository, @Service  @RestController
//spring is CDI - context dependency injection



    //@Entity @Table(table name) @Id (PK)
    //hibernate - ORM framework - object relational mapping - entity management framework

    //@Transactional need not require line number 40 41 43
    public void persist(Object obj) { //persist is a dummy/user defined name

        try {
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(obj); //actual JPA's persist method
            trans.commit();
        } finally { entityManager.close(); }

    }


    public <AnyClass> AnyClass find(Class<AnyClass> theClass, Serializable pk ) {
        AnyClass foundSavingsAccObj = null;
        try {
            foundSavingsAccObj = entityManager.find(theClass, pk);
        } finally { entityManager.close(); }
        return foundSavingsAccObj;
    }


    public <E> List  findAll(String pojoName) {
        try {
            Query query = entityManager.createQuery(" from "+ pojoName);
            return query.getResultList();
        } finally { entityManager.close(); }
    }


    public void merge(Object obj) {

        try {
            entityManager.merge(obj); //<--real call for jdbc here
        } finally { entityManager.close(); }

    }


    public <AnyClass> void remove(Class<AnyClass> theClass, Serializable pk) {
        try {
            AnyClass anyClass = entityManager.find(theClass, pk);
            if(anyClass==null) {
                throw new RuntimeException("Object not found to delete");
            }
            entityManager.remove(anyClass);
        } finally { entityManager.close(); }
    }
}
