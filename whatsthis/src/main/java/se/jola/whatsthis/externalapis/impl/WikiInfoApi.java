package se.jola.whatsthis.externalapis.impl;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.models.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;

@Component
public final class WikiInfoApi {

    private boolean exception = false;

    private String orignalName = "";

    private URLConnection getConnection(String name) throws IOException {

        StringBuilder builder = new StringBuilder();

        builder.append("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts|info&exintro=&inprop=url&explaintext=&titles=").append(URLEncoder.encode(name, "UTF-8"));

        URL url = new URL(builder.toString());

        return url.openConnection();
    }

    public Location getInfoAboutLocation(Location location) throws ApiException {

        try {

            URLConnection connection = getConnection(location.getName());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            reader.close();

            return getInformationFromJson(builder.toString(), location);

        } catch (IOException e) {
            throw new ApiException("Couldnt get wiki-info of: " + location.getName());

        } catch (JSONException e) {
            if (!exception) {
                exception = !exception;
                location.setName(location.getName().split(" ")[0]);
                return getInfoAboutLocation(location);
            } else {
                return location;
            }

        }
    }

    private Location getInformationFromJson(String json, Location location) {

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsObject = jsonObject.getJSONObject("query").getJSONObject("pages");
        String objectId = "";

        Iterator iterator = jsObject.keys();

        while (iterator.hasNext()) {
            objectId = (String) iterator.next();
        }

        JSONObject infoObject = jsObject.getJSONObject(objectId);

        location.setInformation(infoObject.getString("extract"));
        location.setInfoLink(infoObject.getString("fullurl"));

        return location;
    }

}
