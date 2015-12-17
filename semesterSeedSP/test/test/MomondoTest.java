/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import entity.MomondoFlight;
import entity.Passenger;
import entity.Reservation;
import entity.Urls;
import facades.FluffyPandaFacade;
import facades.MomondoFacade;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hamcrest.Matchers;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author steffen
 */
public class MomondoTest {

    EntityManagerFactory emf;
    MomondoFacade moFacade;
    EntityManager em;
    SimpleDateFormat formatter;

    public MomondoTest() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        moFacade = new MomondoFacade();
        em = emf.createEntityManager();
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    @Test
    public void getAllUrlsSuccess() {

        List<Urls> urlslist = moFacade.getAllUrls();

        assertEquals(urlslist.size(), 6);
    }

    @Test
    public void getAllUrlsFails() {

        List<Urls> urlslist = moFacade.getAllUrls();

        assertFalse(urlslist.size() == 70);
    }

    @Test
    public void getReservationByUsernameSuccess() {

        List<Reservation> resList = moFacade.getReservationByUsername("test");

        assertEquals(resList.size(), 5);
    }

    @Test
    public void getReservationByUsernameNoResult() {

        List<Reservation> resList = moFacade.getReservationByUsername("panda");

        assertEquals(resList.size(), 0);
    }
    
    @Test
    public void saveReservationSuccess(){
    
    Reservation res = new Reservation("fluffypanda-test", "CPH", "GOH", "01-01-2016", 250, 1, "Pandee");
    Passenger p = new Passenger("Darth","Vader");
    res.addPassengers(p);
    moFacade.saveReservation("user", res);
    
    List<Reservation> resList = moFacade.getReservationByUsername("user");
    
    assertEquals(resList.get(0).getFlightID(), "fluffypanda-test");
    }
    
    @Test(expected = Exception.class)
    public void saveReservationFails(){
    
    Reservation res = new Reservation("fluffypanda-test", "CPH", "GOH", "01-01-2016", 250, 1, "Pandee");
    Passenger p = new Passenger("Darth","Vader");
    res.addPassengers(p);
    moFacade.saveReservation("humelibuhumelimulibubeli", res);
    }
    
    @Test
    public void getFlightsSimpleSuccess() throws InterruptedException, ExecutionException{
    
    List<MomondoFlight> moflightList = moFacade.getFlightsSimple("cph","2016-01-01T15:00:00.000Z",1);
        assertNotNull(moflightList);
    }
    
    @Test(expected = Exception.class)
    public void getFlightsSimpleFails() throws InterruptedException, ExecutionException{
    
    List<MomondoFlight> moflightList = moFacade.getFlightsSimple("nuuk","2016-01-01T15:00:00.000Z",1);
    }
    
      @Test
    public void getFlightsAdvancedSuccess() throws InterruptedException, ExecutionException{
    
    List<MomondoFlight> moflightList = moFacade.getFlightsAdvanced("cph","stn","2016-05-01T15:00:00.000Z",1);
        assertNotNull(moflightList);
    }
    
    @Test(expected = Exception.class)
    public void getFlightsAdvancedFails() throws InterruptedException, ExecutionException{
    
    List<MomondoFlight> moflightList = moFacade.getFlightsAdvanced("nuuk","hubabubbalandtilhøjre","2016-05-01T15:00:00.000Z",1);
    }
}
