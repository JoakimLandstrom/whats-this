package se.jola.whatsthis.externalapis.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.models.Location;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

@Component
public final class WikiInfoApi {

    private RestTemplate restTemplate = new RestTemplate();

    private boolean exception;

    private String getUrl(String name) throws UnsupportedEncodingException {

        StringBuilder builder = new StringBuilder();

        return builder.append("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts|info&exintro=&inprop=url&explaintext=&titles=").append(name.replace(" ", "%20")).toString();

    }

    public Location getInfoAboutLocationName(Location location) throws ApiException {

        try {

            String jsonResponse = restTemplate.getForObject(getUrl(location.getName()), String.class);

            return getInformationFromJson(jsonResponse, location);

        } catch (UnsupportedEncodingException e) {
            throw new ApiException("Couldnt get wiki-info of: " + location.getName());
        } catch (JSONException e) {

            if(!exception) {
                exception = !exception;
                Location newLocation = new Location(location.getName(), location.getVicinity());

                newLocation.setName(refactorName(newLocation));
                newLocation = getInfoAboutLocationName(location);

                if (newLocation.getExtract() != null && !newLocation.getExtract().isEmpty() && partiallyContains(newLocation.getExtract(), location.getName())) {
                    return newLocation;
                }
            }

            return location;
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

        location.setExtract(infoObject.getString("extract"));
        location.setFullUrl(infoObject.getString("fullUrl"));

        return location;
    }

    private boolean partiallyContains(String container, String key) {

        if(key == null || key.isEmpty() || key.equals(" ")){
            return false;
        }

        String[] keys = key.split(" ");

        int score = 0;

        for (String partialKey : keys) {
            if (container.contains(partialKey)) {
                score++;
            }
        }

        System.out.println(container);
        return (keys.length/(float)score < 0.5);
    }

    private String refactorName(Location location){

        String[] partialNames = location.getName().split(" ");

        String name = location.getName();

        if(partialNames.length > 1 && location.getName().contains(location.getVicinity())){
            return name.replace(location.getVicinity(), "");
        }else if(partialNames.length == 2){
            return name.split(" ")[0];
        }else{
           StringBuilder builder = new StringBuilder();

           for(int i = 0; i< partialNames.length-1; i++){
                builder.append(partialNames[i]);
           }
           return builder.toString();
        }
    }

}
