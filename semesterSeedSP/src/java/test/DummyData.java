/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import entity.Airline;
import entity.Airport;
import entity.Flight;
import entity.FlightInstance;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bente
 */
public class DummyData
{
   
    public void dummy() throws ParseException
    {
        
        
        Airline airline = new Airline("FluffyPanda Airways");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        
        Flight flight = new Flight("FPA12244", 255, airline);
       
        airline.addFlight(flight);
       
        Airport airport1 = new Airport("CPH","Copenhagen Airport", "Copenhagen", "+1");
        Airport airport2 = new Airport("BCN","Barcelona Airport", "Barcelona", "+1");
     
        Date date = formatter.parse("2016-01-05T11:00:00.000-0100");
        Date date2 = formatter.parse("2016-01-06T11:00:00.234-0100");
        Date date3 = formatter.parse("2016-01-05T17:00:00.214-0100");
        Date date4 = formatter.parse("2016-01-06T17:00:00.111-0100");
        
        
        FlightInstance flightInstance = new FlightInstance(date,240,"c43",airport1,airport2,255,80.00,flight);
        FlightInstance flightInstance2 = new FlightInstance(date2,240,"c43",airport1,airport2,255,80.00,flight);
        FlightInstance flightInstance3 = new FlightInstance(date3,240,"c43",airport2,airport1,255,80.00,flight);
        FlightInstance flightInstance4 = new FlightInstance(date4,240,"c43",airport2,airport1,255,80.00,flight);
        flight.addFlightInstance(flightInstance);
        flight.addFlightInstance(flightInstance2);
        flight.addFlightInstance(flightInstance3);
        flight.addFlightInstance(flightInstance4);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(airline);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
    }
}