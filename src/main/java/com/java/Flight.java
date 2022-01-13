package com.java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity //required
@Table(name="flight_details")
public class Flight {

    @Id //required
    @Column(name="flight_no")
    private int flightNumber;

    @Column(name="flight_name")
    private String flightName;

    @Column(name="flight_source")
    private String source;

    @Column(name="flight_destination")
    private String destination;

    @Column(name="flight_departure_date")
    private LocalDateTime flightDeparture;

    public LocalDateTime getFlightDeparture() {
        return flightDeparture;
    }

    public void setFlightDeparture(LocalDateTime flightDeparture) {
        this.flightDeparture = flightDeparture;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}


