package com.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository{

    //in spring we will autowire below line
    EntityManager entityManager; //make sure it is global and not local

    public FlightRepositoryImpl() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);
    }

    @Override
    //@Transactional
    public void insertFlight(Flight flight) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(flight);
        transaction.commit();
    }

    @Override
    public Flight selectFlightByNumber(int flightNumber) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        return entityManager.find(Flight.class,flightNumber);

    }

    @Override
    public List<Flight> selectAllFlights() {
        return entityManager.createQuery("from Flight").getResultList();
    }

    @Override
    @Transactional
    public void updateFlight(Flight flight) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(flight);
        transaction.commit();
    }

    @Override
    @Transactional
    public void deleteFlight(int flightNumber) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Flight flight = entityManager.find(Flight.class,flightNumber);
        entityManager.remove(flight);
        transaction.commit();
    }
}
