/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Mikkel
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String flightID;
    private String origin;
    private String destination;
    private String date;
    private int flightTime;
    private int numberOfSeats;
    private String reserveeName;
    @OneToMany(mappedBy = "reservation", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Passenger> passengers = new ArrayList();
    
    @ManyToOne
    @JoinColumn(name="User_USERNAME")
    private User user;
    
    

    public Reservation() {
    }

    public Reservation(String flightID, String origin, String destination, String date, int flightTime, int numberOfSeats, String reserveeName) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.flightTime = flightTime;
        this.numberOfSeats = numberOfSeats;
        this.reserveeName = reserveeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getReserveeName() {
        return reserveeName;
    }

    public void setReserveeName(String reserveeName) {
        this.reserveeName = reserveeName;
    }
    
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassengers(Passenger pas) {
        passengers.add(pas);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    

    
    
    
    
}
