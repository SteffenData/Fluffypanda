/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import static com.jayway.restassured.path.json.JsonPath.from;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.ApplicationConfig;

/**
 *
 * @author steffen
 */
public class RestAssuredMomondoTest {

    static Server server;

    public RestAssuredMomondoTest() {
        baseURI = "http://localhost:8082";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        server = new Server(8082);
        ServletHolder servletHolder = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
        servletHolder.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getName());
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(contextHandler);
        server.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        server.stop();
        //waiting for all the server threads to terminate so we can exit gracefully
        server.join();
    }

    @Test
    public void getFlightsSimpleTestSuccess() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/cph/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(200);
    }

    @Test
    public void getFlightsSimpleTestWrongDateFormat() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/cph/2016-01-01T01:00:00/2").
                then().
                statusCode(500);
    }

    @Test
    public void getFlightsSimpleTestWrongOrigin() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/bullerfnis/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(500);
    }

    @Test
    public void getFlightsSimpleNoLoginTest() {

        given().
                contentType("application/json").
                when().
                get("/momondo/cph/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(401);
    }

    @Test
    public void getFlightsAdvancedTestSuccess() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/cph/sxf/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(200);
    }

    @Test
    public void getFlightsAdvancedTestWithCapsOn() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/CPH/SXF/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(200);
    }

    @Test
    public void getFlightsAdvancedTestWithWrongDestination() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/cph/pandaLand/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(500);
    }

    @Test
    public void getFlightsAdvancedTestWithWrongOriginAndDestination() {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/momondo/VaderLand/pandaLand/2016-01-01T01:00:00.000Z/2").
                then().
                statusCode(500);
    }

    @Test
    public void saveReservationSuccess() {

       JsonObject jojoe = hitMedJsonObject();
        
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                body(jojoe).
                when().
                post("/momondo/savereservation/user").
                then().
                statusCode(200);
    }
    
    @Test
    public void saveReservationWithWrongUser() {

       JsonObject jojoe = hitMedJsonObject();
        
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        given().
                contentType("application/json").
                body(jojoe).
                when().
                post("/momondo/savereservation/hamster").
                then().
                statusCode(500);
    }
    
//   @Test
//   public void getReservationByUseName(){
//   
//       JsonObject jojoe = hitMedJsonObject();
//       
//            String json = given().
//                contentType("application/json").
//                body("{'username':'user','password':'test'}").
//                when().
//                post("/login").
//                then().
//                statusCode(200).extract().asString();
//        given().
//                contentType("application/json").
//                body().
//                when().
//                post("/momondo/savereservation/hamster").
//                then().
//                statusCode(500);
//    
//   
//   }
    
    
    public JsonObject hitMedJsonObject(){
    
          JsonObject jo = new JsonObject();
            jo.addProperty("flightID", "humabuma nr 700");
            jo.addProperty("Origin", "cdg");
            jo.addProperty("Destination","cph");
            jo.addProperty("Date","2016-05-05T01:00:00.000Z");
            jo.addProperty("FlightTime",120);
            jo.addProperty("numberOfSeats",1);
            jo.addProperty("ReserveeName","PandaMann");
            
            JsonArray jsonPassengers = new JsonArray();
            JsonObject jo2 = new JsonObject();
            jo2.addProperty("firstName", "Panda");
            jo2.addProperty("lastName", "Mann");
            jsonPassengers.add(jo2);
            
            jo.add("Passengers", jsonPassengers);
            
            return jo;
    }

}
