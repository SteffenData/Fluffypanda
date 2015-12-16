/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author steffen
 */
import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class FlightNotFoundExceptionMapper implements ExceptionMapper<FlightNotFoundException>{
@Context
ServletContext context;
    @Override
    public Response toResponse(FlightNotFoundException f) {
        JsonObject j = new JsonObject();
        j.addProperty("httpError",404 );
        j.addProperty("errorCode", 1);
        j.addProperty("message", f.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(j.toString()).build();
    }
    
}

