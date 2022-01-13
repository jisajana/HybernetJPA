import com.java.Flight;
import com.java.FlightRepository;
import com.java.FlightRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightRepoTestCase {

    @Test
    public void insertFlightRepoTest() {

        //in spring JPA, we wont write below line, rather we will @Autowire it
        FlightRepository flightRepository = new FlightRepositoryImpl();

        Flight flight = new Flight();
        flight.setFlightNumber(106);
        flight.setFlightName("American Airlines");
        flight.setSource("Mumbai");
        flight.setDestination("Florida");
        flight.setFlightDeparture(LocalDateTime.of(2021,12,25, 11,45));
//        flight.setFlightDeparture(LocalDateTime.of(2021,12,27,22,45));
        flightRepository.insertFlight(flight);
    }

    @Test
    public void updateFlightRepoTest() {

        //in spring JPA, we wont write below line, rather we will @Autowire it
        FlightRepository flightRepository = new FlightRepositoryImpl();

        Flight flight = flightRepository.selectFlightByNumber(106);
        flight.setFlightName("Air Airlines");
        //flight.setSource("Mumbai");
        //flight.setDestination("Florida");
        //flight.setFlightDeparture(LocalDateTime.of(2021,12,25, 11,45));
//        flight.setFlightDeparture(LocalDateTime.of(2021,12,27,22,45));
        flightRepository.updateFlight(flight);
    }

    @Test
    public void deleteFlightRepoTest() {

        //in spring JPA, we wont write below line, rather we will @Autowire it
        FlightRepository flightRepository = new FlightRepositoryImpl();

        Flight flight = flightRepository.selectFlightByNumber(106);
        //flight.setFlightName("Air Airlines");
        //flight.setSource("Mumbai");
        //flight.setDestination("Florida");
        //flight.setFlightDeparture(LocalDateTime.of(2021,12,25, 11,45));
//        flight.setFlightDeparture(LocalDateTime.of(2021,12,27,22,45));
        flightRepository.deleteFlight(106);
    }

    @Test
    public void selectFlightRepoTest() {

        //in spring JPA, we wont write below line, rather we will @Autowire it
        FlightRepository flightRepository = new FlightRepositoryImpl();

        Flight flight = flightRepository.selectFlightByNumber(105);
        //flight.setFlightName("Air Airlines");
        //flight.setSource("Mumbai");
        //flight.setDestination("Florida");
        //flight.setFlightDeparture(LocalDateTime.of(2021,12,25, 11,45));
//        flight.setFlightDeparture(LocalDateTime.of(2021,12,27,22,45));
        flightRepository.selectFlightByNumber(105);
    }
}
