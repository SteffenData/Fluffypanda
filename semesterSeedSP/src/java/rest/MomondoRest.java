/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.MomondoFlight;
import facades.MomondoFacade;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import utility.Post;

/**
 *
 * @author steffen
 */
@Path("momondo/")
public class MomondoRest {

    MomondoFacade f;
    Gson gsonDate;
    Post p;
    
    public MomondoRest() {

        f = new MomondoFacade();
        gsonDate = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().create();
        p = new Post();
    }

    @GET
    @Path("{from}/{date}/{numtickets}")
    @Consumes("application/json")
    public Response getFlightsSimple(@PathParam("from") String from, @PathParam("date") String date, @PathParam("numtickets") int numtickets) throws InterruptedException, ExecutionException {

        List<MomondoFlight> momondoFlightList = f.getFlightsSimple(from, date, numtickets);

        JsonArray jsonFlights = new JsonArray();

        for (MomondoFlight m : momondoFlightList) {

            JsonObject jo = new JsonObject();
            jo.addProperty("airline", m.getAirline());
            jo.addProperty("flightID", m.getFlightId());
            jo.addProperty("origin", m.getOrigin());
            jo.addProperty("destination", m.getDestination());
            jo.addProperty("date", m.getDate());
            jo.addProperty("numberOfSeats", m.getNumberOfSeats());
            jo.addProperty("totalPrice", m.getTotalPrice());
            jo.addProperty("traveltime", m.getTravelTime());
            jo.addProperty("normalurl", m.getNormalUrl());

            jsonFlights.add(jo);
        }

        return Response.status(Response.Status.OK).entity(jsonFlights.toString()).build();
    }

    @GET
    @Path("{from}/{to}/{date}/{numtickets}")
    @Consumes("application/json")
    public Response getFlightsAdvanced(@PathParam("from") String from, @PathParam("to") String to, @PathParam("date") String date, @PathParam("numtickets") int numtickets) throws InterruptedException, ExecutionException {

        List<MomondoFlight> momondoFlightList = f.getFlightsAdvanced(from, to, date, numtickets);

        JsonArray jsonFlights = new JsonArray();

        for (MomondoFlight m : momondoFlightList) {

            JsonObject jo = new JsonObject();
            jo.addProperty("airline", m.getAirline());
            jo.addProperty("flightID", m.getFlightId());
            jo.addProperty("origin", m.getOrigin());
            jo.addProperty("destination", m.getDestination());
            jo.addProperty("date", m.getDate());
            jo.addProperty("numberOfSeats", m.getNumberOfSeats());
            jo.addProperty("totalPrice", m.getTotalPrice());
            jo.addProperty("traveltime", m.getTravelTime());
            jo.addProperty("normalurl", m.getNormalUrl());

            jsonFlights.add(jo);
        }

        return Response.status(Response.Status.OK).entity(jsonFlights.toString()).build();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response reservationRequest(String jsonString) throws IOException{
       JsonObject js = new JsonParser().parse(jsonString).getAsJsonObject();
       String url = js.get("url").getAsString();
       js.remove("url");
       String finalJs = js.toString();
       
       String stringResponse = p.postReservation(url, finalJs);
       return Response.status(Response.Status.OK).entity(stringResponse).build();
    }
}
