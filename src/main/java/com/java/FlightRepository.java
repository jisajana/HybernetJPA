package com.java;

import java.util.List;

public interface FlightRepository {

    void insertFlight(Flight flight);

    Flight selectFlightByNumber(int flightNumber);
    List<Flight> selectAllFlights();


    void updateFlight(Flight flight);


    void deleteFlight(int flightNumber);


}
