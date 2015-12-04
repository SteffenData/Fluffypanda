/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.MomondoFlight;
import entity.Urls;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
            urlsList = em.createQuery("select u FROM Urls u",Urls.class).getResultList();

        } finally {
            em.close();
        }

        return urlsList;
    }

    public List<MomondoFlight> getFlightsSimple (String origin, String date, int numberOfTickets) {
       
        
        
        
        
        return null;
    }
}
