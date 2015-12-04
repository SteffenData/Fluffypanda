/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FluffyPandaFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author steffen
 */


public class MomondoRest {

    FluffyPandaFacade f;
    Gson gsonDate;

    public MomondoRest() {

        f = new FluffyPandaFacade();
        gsonDate = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().create();

    }
    
//    @GET
//    @Path("{from}/{date}/{numtickets}")
//    @Produces("application/json")
//    public Response 
    
}