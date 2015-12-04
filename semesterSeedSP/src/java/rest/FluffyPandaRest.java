/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Airline;
import entity.Airport;
import entity.FlightInstance;
import facades.FluffyPandaFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author steffen
 */
@Path("flightinfo/")
public class FluffyPandaRest {

    FluffyPandaFacade f;
    Gson gsonDate;

    public FluffyPandaRest() {

        f = new FluffyPandaFacade();
        gsonDate = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().create();

    }

    @GET
    @Path("{from}/{date}/{numtickets}")
    @Produces("application/json")
    public Response getAirlinesByOriginDateNumberOfTickets(@PathParam("from") String from, @PathParam("date") String date, @PathParam("numtickets") int numtickets) throws ParseException {

         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = formatter.parse(date);
        
        JsonArray jsonFlights = new JsonArray();
        JsonObject jsonAirline = new JsonObject();
        List<FlightInstance> Flightlist = f.getFlightsByOriginDateNumberOfTickets(from, date2, numtickets);
        FlightInstance fi = Flightlist.get(0);
        jsonAirline.addProperty("airline", fi.getFlight().getAirline().getName());

        for (FlightInstance fl : Flightlist) {

            jsonFlights.add(flightToJson(fl));
        }
        jsonAirline.add("flights", jsonFlights);
        return Response.status(Response.Status.OK).entity(jsonAirline.toString()).build();
//        return jsonAirline.toString();
    }

    public JsonObject flightToJson(FlightInstance fl) {

        JsonObject jsonIndividualFlight = new JsonObject();
        String jsondate = gsonDate.toJson(fl.getDepartureDate());
        jsonIndividualFlight.addProperty("date", jsondate);
        jsonIndividualFlight.addProperty("numberOfSeats", fl.getAvailableSeats());
        jsonIndividualFlight.addProperty("totalPrice", fl.getPrice());
        jsonIndividualFlight.addProperty("flightID", fl.getFlightNumber());
        jsonIndividualFlight.addProperty("traveltime", fl.getFlightTime());
        jsonIndividualFlight.addProperty("destination", fl.getDestination().getIataCode());
        jsonIndividualFlight.addProperty("origin", fl.getOrigin().getIataCode());

        return jsonIndividualFlight;
    }
    
    
}
