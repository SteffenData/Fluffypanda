/*
 * To change this license header, choose License Headers inputStream Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template inputStream the editor.
 */
package utility;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.MomondoFlight;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author steffen
 */
public class GetFlightThread implements Callable<List<MomondoFlight>>
{

    private URL url = null;
    private String finalUrl = "";
    private URLConnection urlConnection = null;
    private InputStreamReader inputStream = null;
    private final StringBuilder stringBuilder = new StringBuilder();
    private BufferedReader bufferedReader = null;
    private String airlineName;
    private List<MomondoFlight> momondoFlights = null;
    private Gson gson = null;
    private JsonObject jsonObject;
    private JsonArray jsonArray;

    public GetFlightThread(String finalUrl)
    {
        this.finalUrl = finalUrl;
    }

    @Override
    public List<MomondoFlight> call() throws Exception
    {
        momondoFlights = new ArrayList<>();
        jsonObject = new JsonObject();
        gson = new Gson();
        url = new URL(finalUrl);
        urlConnection = url.openConnection();
        if (urlConnection != null && urlConnection.getInputStream() != null)
        {

            inputStream = new InputStreamReader(urlConnection.getInputStream(), Charset.defaultCharset());
            bufferedReader = new BufferedReader(inputStream);
            int cp;
            while ((cp = bufferedReader.read()) != -1)
            {
                stringBuilder.append((char) cp);
            }
            jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();

            if (jsonObject.get("airline") == null)
            {
                return momondoFlights;
            } else
            {
                airlineName = jsonObject.get("airline").getAsString();
                jsonArray = jsonObject.get("flights").getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++)
                {
                    JsonObject json = (JsonObject) jsonArray.get(i);
//                System.out.println("jsonups" + json.get("date"));
////                String d = json.get("date").getAsString();
////                System.out.println("d ###" + d);
//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//                Date date = df.parse(json.get("date").getAsString());
//             
//                System.out.println("date ###" + date);
                    MomondoFlight f = new MomondoFlight(
                            airlineName,
                            json.get("flightID").getAsString(),
                            //                        date,
                            json.get("date").getAsString(),
                            json.get("numberOfSeats").getAsInt(),
                            json.get("totalPrice").getAsDouble(),
                            json.get("traveltime").getAsInt(),
                            json.get("destination").getAsString(),
                            json.get("origin").getAsString());
                    momondoFlights.add(f);
                }
                return momondoFlights;
            }
        }
            return momondoFlights;
    }
}
