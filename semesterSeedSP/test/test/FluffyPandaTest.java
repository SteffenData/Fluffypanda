/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import entity.Airport;
import entity.FlightInstance;
import facades.FluffyPandaFacade;
import facades.MomondoFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author steffen
 */
public class FluffyPandaTest {

    EntityManagerFactory emf;
    FluffyPandaFacade fpFacade;
    EntityManager em;
    SimpleDateFormat formatter;

    public FluffyPandaTest() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        fpFacade = new FluffyPandaFacade();
        em = emf.createEntityManager();
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    @Test
    public void getAllFlights() {

        List<FlightInstance> flight = fpFacade.getAllFlights();

        assertEquals(flight.size(), 4);
    }

    @Test
    public void getFlightsByOriginDateNumberOfTicketsSuccess() throws ParseException {

        Date date = formatter.parse("2016-01-05T11:00:00.000-0100");

        List<FlightInstance> flight = fpFacade.getFlightsByOriginDateNumberOfTickets("cph", date, 2);

        assertEquals(flight.size(), 1);
    }

    @Test
    public void getFlightsByOriginDateNumberOfTicketsFails() throws ParseException {

        Date date = formatter.parse("2016-11-05T11:00:00.000-0100");

        List<FlightInstance> flight = fpFacade.getFlightsByOriginDateNumberOfTickets("cph", date, 2);

        assertEquals(flight.size(), 0);
    }

    @Test
    public void getFlightsByOriginDestinationDateNumberOfTicketsSuccess() throws ParseException {

        Date date = formatter.parse("2016-01-05T11:00:00.000-0100");

        List<FlightInstance> flight = fpFacade.getFlightsByOriginDestinationDateNumberOfTickets("cph", "bcn", date, 2);

        assertEquals(flight.size(), 1);
    }

    @Test
    public void getFlightsByOriginDestinationDateNumberOfTicketsParametersSuccess() throws ParseException {

        Date date = formatter.parse("2016-01-05T11:00:00.000-0100");

        List<FlightInstance> flight = fpFacade.getFlightsByOriginDestinationDateNumberOfTickets("cph", "bcn", date, 2);

        FlightInstance flightinstance = flight.get(0);

        assertEquals(flightinstance.getOrigin().getIataCode(), "CPH");
        assertEquals(flightinstance.getDestination().getIataCode(), "BCN");
    }

    @Test
    public void getFlightsByOriginDestinationDateNumberOfTicketsFails() throws ParseException {

        Date date = formatter.parse("2016-12-05T11:00:00.000-0100");

        List<FlightInstance> flight = fpFacade.getFlightsByOriginDestinationDateNumberOfTickets("cph", "bcn", date, 2);

        assertEquals(flight.size(), 0);
    }
    
      @Test
    public void getAirportByIataCodeSuccess(){

     Airport airport = fpFacade.getAirportByIataCode("BCN");
          assertEquals(airport.getName(), "Barcelona Airport");
    }
    
       @Test
    public void getAirportByIataCodeFails(){

     Airport airport = fpFacade.getAirportByIataCode("KBH");
          assertEquals(airport, null);
    }
}
