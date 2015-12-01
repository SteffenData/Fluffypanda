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
    @Temporal(javax.persistence.TemporalType.TIME)
    private Time departureTime;
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
}
