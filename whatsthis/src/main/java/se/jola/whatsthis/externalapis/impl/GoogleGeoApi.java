package se.jola.whatsthis.externalapis.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.models.Location;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public final class GoogleGeoApi {

    private URLConnection getConnection(String lat, String lng) throws IOException {

        StringBuilder builder = new StringBuilder();

        builder.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=").append(lat).append(",")
                .append(lng).append("&type=point_of_interest|locality|natural_feature|place_of_worship|establishment&radius=200&key=").append(getKey());
        URL url = new URL(builder.toString());

        return url.openConnection();
    }


    public List<Location> getLocations(String lat, String lng) throws ApiException {

        try {
            URLConnection connection = getConnection(lat, lng);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            reader.close();

            return retrieveLocations(builder.toString());

        } catch (IOException e) {
            throw new ApiException("Couldnt get info from lat:" + lat + "and lng:" + lng + " from google");
        }
    }

    private String getKey() throws IOException {

        Properties properties = new Properties();

        InputStream inputStream = new FileInputStream("src/main/resources/application.properties");

        properties.load(inputStream);

        return properties.getProperty("key");
    }

    private List<Location> retrieveLocations(String jsonFile) {

        JSONObject jsonObject = new JSONObject(jsonFile);

        JSONArray jsonArray = jsonObject.getJSONArray("results");

        List<Location> locationList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsObject = (JSONObject) jsonArray.get(i);

            Location location = new Location(jsObject.getString("name"), jsObject.getString("vicinity"));

            locationList.add(location);
        }

        return locationList;
    }

}
