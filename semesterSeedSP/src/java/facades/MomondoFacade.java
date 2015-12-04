/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.MomondoFlight;
import entity.Urls;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import utility.GetFlightThread;

/**
 *
 * @author steffen
 */
public class MomondoFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    public MomondoFacade() {
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Urls> getAllUrls() {

        EntityManager em = getEntityManager();
        List<Urls> urlsList;

        try {
            urlsList = em.createQuery("select u FROM Urls u", Urls.class).getResultList();

        } finally {
            em.close();
        }

        return urlsList;
    }

    public List<MomondoFlight> getFlightsSimple(String origin, String date, int numberOfTickets) throws InterruptedException, ExecutionException {

        String finalUrl;
        List<Urls> urlsList = getAllUrls();
        List<MomondoFlight> finalMomondoFlights = new ArrayList();
        List<Future<List<MomondoFlight>>> futureMomondoFlight = new ArrayList();

        ExecutorService ex = Executors.newFixedThreadPool(8);

        for (Urls urls : urlsList) {
            finalUrl = urls.getUrls() + "api/flightinfo/" + origin + "/" + date + "/" + numberOfTickets;
            Future<List<MomondoFlight>> futureFlights = ex.submit(new GetFlightThread(finalUrl));
            futureMomondoFlight.add(futureFlights);
        }

        for (Future<List<MomondoFlight>> futureFlights : futureMomondoFlight) {
            List<MomondoFlight> f1 = futureFlights.get();
            for (MomondoFlight f2 : f1) {
                finalMomondoFlights.add(f2);
            }
        }
        return finalMomondoFlights;
    }
}
