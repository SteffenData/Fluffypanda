/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.Airline;
import entity.Airport;
import entity.FlightInstance;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author steffen
 */
public class FluffyPandaFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public FluffyPandaFacade() {
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<FlightInstance> getFlightsByOriginDateNumberOfTickets(String code, Date date, int numberOfTickets) {

        EntityManager em = getEntityManager();

        List<FlightInstance> flightList;
        Airport origin = getAirportByIataCode(code);

        try {
            // Airline = a, Flight = f, Flightinstance = i,  Airport = p
            TypedQuery<FlightInstance> q = em.createQuery("SELECT f FROM FlightInstance f "
                    + "WHERE f.origin =:origin AND f.departureDate =:date AND f.availableSeats >=:numberOfTickets ", FlightInstance.class);
            q.setParameter("origin", origin);
            q.setParameter("date", date);
            q.setParameter("numberOfTickets", numberOfTickets);
            flightList = q.getResultList();

        } finally {
            em.close();
        }

        return flightList;
    }

    public List<FlightInstance> getAllFlights() {
        EntityManager em = getEntityManager();
        List<FlightInstance> currencyList;
        try {
            currencyList = em.createQuery("SELECT f FROM FlightInstance f", FlightInstance.class).getResultList();
            return currencyList;
        } finally {
            em.close();
        }
    }

    public Airport getAirportByIataCode(String code) {

        EntityManager em = getEntityManager();
        Airport airport;

        try {

            airport = em.find(Airport.class, code);

        } finally {
            em.close();
        }
        return airport;
    }

}
