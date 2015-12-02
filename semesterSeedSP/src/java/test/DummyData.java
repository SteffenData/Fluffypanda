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
        Airline airline = new Airline("FluffyPandaAirways");
        Flight flight = new Flight("FPA12244", 255, airline);
//        airline.addFlight(flight);
        
        Airport airport1 = new Airport("CPH","Copenhagen Airport", "Copenhagen", "+1");
        Airport airport2 = new Airport("NU","Nuuk Airport", "Nuuk", "-2");
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse("05-11-2016");
        FlightInstance flightInstance = new FlightInstance("11.00",date,240,"c43",airport1,airport2,255,2000.00,flight);
        flight.addFlightInstance(flightInstance);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(airline);
//            em.persist(flight);
//            em.persist(airport1);
//            em.persist(airport2);
//            em.persist(flightInstance);
            em.getTransaction().commit();
        } finally
        {
            em.close();
        }
    }
}