/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Mikkel
 */
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String iataCode;
    private String name;
    private String city;
    private String timeZone;
    @OneToOne(mappedBy = "origin")
    private FlightInstance origin;
    @OneToOne(mappedBy = "destination")
    private FlightInstance destination;

    public Airport() {
    }

    public Airport(String iataCode, String name, String city, String timeZone, FlightInstance origin, FlightInstance destination)
    {
        this.iataCode = iataCode;
        this.name = name;
        this.city = city;
        this.timeZone = timeZone;
        this.origin = origin;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public FlightInstance getOrigin() {
        return origin;
    }

    public void setOrigin(FlightInstance origin) {
        this.origin = origin;
    }

    public FlightInstance getDestination() {
        return destination;
    }

    public void setDestination(FlightInstance destination) {
        this.destination = destination;
    }
    
}
