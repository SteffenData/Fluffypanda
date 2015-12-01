/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Mikkel
 */
@Entity
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String flightnumber;
    private int numberOfSeats;
    
    @ManyToOne
    private Airline airline;
    @OneToMany(mappedBy = "flight") @ElementCollection
    private List<FlightInstance> flightInstances = new ArrayList();

    public Flight() {
    }

    public Flight(String flightnumber, int numberOfSeats, Airline airline)
    {
        this.flightnumber = flightnumber;
        this.numberOfSeats = numberOfSeats;
        this.airline = airline;
    }
    
    public void addFlightInstance(FlightInstance flightInstance){
        flightInstances.add(flightInstance);
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
