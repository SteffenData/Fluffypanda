/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mikkel
 */
@Entity
public class FlightInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String departureTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date departureDate;
    private int flightTime;
    private String flightNumber;
    @OneToOne
    private Airport destination;
    @OneToOne
    private Airport origin;
    private int availableSeats;
    private double price;
    @ManyToOne
    private Flight flight;

    public FlightInstance()
    {
    }

    public FlightInstance(String departureTime, Date departureDate, int flightTime, String flightNumber, Airport destination, Airport origin, int availableSeats, double price, Flight flight)
    {
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.flightTime = flightTime;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.origin = origin;
        this.availableSeats = availableSeats;
        this.price = price;
        this.flight = flight;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public Date getDepartureDate()
    {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate)
    {
        this.departureDate = departureDate;
    }

    public int getFlightTime()
    {
        return flightTime;
    }

    public void setFlightTime(int flightTime)
    {
        this.flightTime = flightTime;
    }

    public String getFlightNumber()
    {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public Airport getDestination()
    {
        return destination;
    }

    public void setDestination(Airport destination)
    {
        this.destination = destination;
    }

    public Airport getOrigin()
    {
        return origin;
    }

    public void setOrigin(Airport origin)
    {
        this.origin = origin;
    }

    public int getAvailableSeats()
    {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats)
    {
        this.availableSeats = availableSeats;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Flight getFlight()
    {
        return flight;
    }

    public void setFlight(Flight flight)
    {
        this.flight = flight;
    }
    
}
