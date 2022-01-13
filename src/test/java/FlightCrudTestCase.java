import com.java.Flight;
import org.junit.Test;

import javax.persistence.*;
import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;

public class FlightCrudTestCase {

//select flight0_.flight_no as flight_n1_0_0_,
// flight0_.flight_destination as flight_d2_0_0_,
// flight0_.flight_departure_date as flight_d3_0_0_,
// flight0_.flight_name as flight_n4_0_0_,
// flight0_.flight_source as flight_s5_0_0_
// from flight_details flight0_ where flight0_.flight_no=?


    @Test
    public void testSelectFlight() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);

        Flight flight = entityManager.find(Flight.class,103);
        Assertions.assertNotNull(flight); //means if flight is not null, ie we got the flight
        System.out.println("Flight Number : "+flight.getFlightNumber());
        System.out.println("Flight name   : "+flight.getFlightName());
        System.out.println("Flight source : "+flight.getSource());
        System.out.println("Flight destination : "+flight.getDestination());
        System.out.println("Flight date   : "+flight.getFlightDeparture());

    }

    @Test
    public void testSelectAllFlights() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);

        //Flight flight = entityManager.find(Flight.class,103);
        Query query = entityManager.createQuery("from Flight") ; //JPQL this is POJO and not table name

        List<Flight> flightList = query.getResultList(); //it will get the Result and populate to list

        Assertions.assertNotNull(flightList); //means if flightList is not null, ie we got the flightList
        Assertions.assertTrue(flightList.size()>0);// flightList is not empty

        for(Flight flight : flightList) {
            System.out.println("Flight Number : " + flight.getFlightNumber());
            System.out.println("Flight name   : " + flight.getFlightName());
            System.out.println("Flight source : " + flight.getSource());
            System.out.println("Flight destin : " + flight.getDestination());
            System.out.println("Flight date   : " + flight.getFlightDeparture());
            System.out.println("----------------------");
        }
    }

    @Test
    public void testUpdateFlight() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);

        //below object is THE TRANSIENT OBJECT
        // Flight flight1 = new Flight(); //this is not an attached object, it has no connection to DB

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Transaction started.....");

        entityTransaction.begin(); // START THE TRANSACTION

        //BELOW OBJECT IS "ATTACHED" TO THE JPA SESSION
        Flight flight = entityManager.find(Flight.class,106);//A ROW IS FETCHED  VIA "select query"
        Assertions.assertNotNull(flight); //means if flight is not null, ie we got the flight
        System.out.println("Current Flight Number : "+flight.getFlightNumber()); //ATTACHED DETAILS
        System.out.println("Current Flight name   : "+flight.getFlightName());//ATTACHED DETAILS
        System.out.println("Current Flight source : "+flight.getSource());//ATTACHED DETAILS
        System.out.println("Current Flight destin : "+flight.getDestination());//ATTACHED DETAILS
        System.out.println("Current Flight date   : "+flight.getFlightDeparture());//ATTACHED DETAILS

        System.out.println("MODIFYING THESE DETAILS....by calling FEW setter methods ");
        flight.setFlightName("AMERICAN AIRLINES"); //CHANGING THE ATTACHED DETAILS -  never change the primary key
        flight.setSource("NEW YORK"); //CHANGING THE ATTACHED DETAILS
        flight.setDestination("GERMANY"); //CHANGING THE ATTACHED DETAILS
        entityManager.merge(flight); // THIS WOULD TRIGGER THE "UPDATE QUERY" SINCE THE OBJ IS CHANGED

        entityTransaction.commit(); // FINISH the transaction by commiting records to the DB

    }
//update flight_details set flight_destination=?, flight_departure_date=?, flight_name=?, flight_source=? where flight_no=?

    @Test
    public void testDeleteFlight() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);

        //below object is THE TRANSIENT OBJECT
        // Flight flight1 = new Flight(); //this is not an attached object, it has no connection to DB

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Transaction started.....");

        entityTransaction.begin(); // START THE TRANSACTION

        //BELOW OBJECT IS "ATTACHED" TO THE JPA SESSION
        Flight flight = entityManager.find(Flight.class,106);//A ROW IS FETCHED  VIA "select query"
        Assertions.assertNotNull(flight); //means if flight is not null, ie we got the flight
        System.out.println("Current Flight Number : "+flight.getFlightNumber()); //ATTACHED DETAILS
        System.out.println("Current Flight name   : "+flight.getFlightName());//ATTACHED DETAILS
        System.out.println("Current Flight source : "+flight.getSource());//ATTACHED DETAILS
        System.out.println("Current Flight destin : "+flight.getDestination());//ATTACHED DETAILS
        System.out.println("Current Flight date   : "+flight.getFlightDeparture());//ATTACHED DETAILS

        System.out.println("DELETING THIS OBJECT..... ");
        entityManager.remove(flight); // THIS WOULD TRIGGER THE "DELETE QUERY" SINCE THE OBJ IS CHANGED

        entityTransaction.commit(); // FINISH the transaction by committing records to the DB

        //delete from flight_details where flight_no=?
    }


    @Test
    public void testInsertFlights() {
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
        flight.setFlightNumber(102);
        flight.setFlightName("Indian Airlines");
        flight.setSource("Mumbai");
        flight.setDestination("New York");
        flight.setFlightDeparture(LocalDateTime.of(2021,12,27,22,45));


        Flight flight1 = new Flight();
        flight1.setFlightNumber(103);
        flight1.setFlightName("Air India");
        flight1.setSource("Mumbai");
        flight1.setDestination("New Jersey");
        flight1.setFlightDeparture(LocalDateTime.of(2021,12,23,10,45));

        Flight flight2 = new Flight();
        flight2.setFlightNumber(104);
        flight2.setFlightName("Go Air");
        flight2.setSource("Mumbai");
        flight2.setDestination("Washington");
        flight2.setFlightDeparture(LocalDateTime.of(2021,12,23,15,45));

        Flight flight3 = new Flight();
        flight3.setFlightNumber(105);
        flight3.setFlightName("Lufthansa");
        flight3.setSource("Mumbai");
        flight3.setDestination("Germany");
        flight3.setFlightDeparture(LocalDateTime.of(2021,12,25,23,45));

        Flight flight4 = new Flight();
        flight4.setFlightNumber(106);
        flight4.setFlightName("British Airways");
        flight4.setSource("Mumbai");
        flight4.setDestination("London");
        flight4.setFlightDeparture(LocalDateTime.of(2021,12,25,13,45));

        Flight flight5 = new Flight();
        flight5.setFlightNumber(101);
        flight5.setFlightName("Nepal Airlines");
        flight5.setSource("Kathmandu");
        flight5.setDestination("New York");
        flight5.setFlightDeparture(LocalDateTime.of(2021,12,27,19,45));

        entityManager.persist(flight); // <-- fires the insert query, WOW!!!
        entityManager.persist(flight1);
        entityManager.persist(flight2);
        entityManager.persist(flight3);
        entityManager.persist(flight4);
        entityManager.persist(flight5);

        entityTransaction.commit();
        System.out.println("Transaction committed....");


    }

    @Test
    public void testSelectedFlightsWithWhereClause() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : "+entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : "+entityManager);

        //Flight flight = entityManager.find(Flight.class,103);
        Query query = entityManager.createQuery("from Flight f where f.source=:src and f.destination=:dest") ; //JPQL this is POJO and not table name
        query.setParameter("src","Mumbai");
        query.setParameter("dest","Germany");

        List<Flight> flightList = query.getResultList(); //it will get the Result and populate to list

        Assertions.assertNotNull(flightList); //means if flightList is not null, ie we got the flightList
        Assertions.assertTrue(flightList.size()>0);// flightList is not empty

        for(Flight flight : flightList) {
            System.out.println("Flight Number : " + flight.getFlightNumber());
            System.out.println("Flight name   : " + flight.getFlightName());
            System.out.println("Flight source : " + flight.getSource());
            System.out.println("Flight destin : " + flight.getDestination());
            System.out.println("Flight date   : " + flight.getFlightDeparture());
            System.out.println("----------------------");
        }
    }
}



