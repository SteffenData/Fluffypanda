/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Airport;
import entity.FlightInstance;
import facades.FluffyPandaFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author steffen
 */
public class TestFlights {
    
    public static void main(String[] args) throws ParseException {
        
        
           Airport airport1 = new Airport("CPH","Copenhagen Airport", "Copenhagen", "+1");
        FluffyPandaFacade fp = new FluffyPandaFacade();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse("07-11-2016");
       
        List<FlightInstance> FList = fp.getFlightsByOriginDateNumberOfTickets(airport1, date, 4);
        System.out.println(" maaskedata   " + FList);
        
    }
    
}
