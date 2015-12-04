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
   
    public static void main(String[] args) throws ParseException
    {
        Airline airline = new Airline("FluffyPanda Airways");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        Flight flight = new Flight("FPA12244", 255, airline);
       
        airline.addFlight(flight);
       
        Airport airport1 = new Airport("CPH","Copenhagen Airport", "Copenhagen", "+1");
        Airport airport2 = new Airport("NU","Nuuk Airport", "Nuuk", "-2");
         
        Date date = formatter.parse("2016-11-05");
        Date date2 = formatter.parse("2016-11-06");
        FlightInstance flightInstance = new FlightInstance("11.00",date,240,"c43",airport1,airport2,255,2000.00,flight);
        FlightInstance flightInstance2 = new FlightInstance("11.00",date2,240,"c43",airport1,airport2,255,2000.00,flight);
        FlightInstance flightInstance3 = new FlightInstance("17.00",date,240,"c43",airport2,airport1,255,2000.00,flight);
        FlightInstance flightInstance4 = new FlightInstance("17.00",date2,240,"c43",airport2,airport1,255,2000.00,flight);
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