package com.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class FlightCrudTest {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : "+entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");
        Flight flight = new Flight();
        flight.setFlightNumber(101);
        flight.setFlightName("Nepal Airlines");
        flight.setSource("Kathmandu");
        flight.setDestination("New York");
        flight.setFlightDeparture(LocalDateTime.of(2021,12,23,10,45));

        entityManager.persist(flight); // <-- fires the insert query, WOW!!!
        entityTransaction.commit();
        System.out.println("Transaction committed....");

    }
}