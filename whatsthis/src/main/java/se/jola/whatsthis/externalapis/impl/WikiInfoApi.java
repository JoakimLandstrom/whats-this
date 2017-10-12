package se.jola.whatsthis.externalapis.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.models.LocationResponse;

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

    public LocationResponse getInfoAboutLocationName(LocationResponse locationResponse) throws ApiException {

        try {

            String jsonResponse = restTemplate.getForObject(getUrl(locationResponse.getName()), String.class);

            return getInformationFromJson(jsonResponse, locationResponse);

        } catch (UnsupportedEncodingException | RestClientException e) {
            throw new ApiException("Couldnt get wiki-info of: " + locationResponse.getName());
        } catch (JSONException e) {

            if(!exception) {
                exception = !exception;
                LocationResponse newLocationResponse = new LocationResponse(locationResponse.getName(), locationResponse.getVicinity());

                newLocationResponse.setName(refactorName(newLocationResponse));
                newLocationResponse = getInfoAboutLocationName(locationResponse);

                if (newLocationResponse.getExtract() != null && !newLocationResponse.getExtract().isEmpty() && partiallyContains(newLocationResponse.getExtract(), locationResponse.getName())) {
                    return newLocationResponse;
                }
            }

            return locationResponse;
        }
    }

    private LocationResponse getInformationFromJson(String json, LocationResponse locationResponse) {

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsObject = jsonObject.getJSONObject("query").getJSONObject("pages");
        String objectId = "";

        Iterator iterator = jsObject.keys();

        while (iterator.hasNext()) {
            objectId = (String) iterator.next();
        }

        JSONObject infoObject = jsObject.getJSONObject(objectId);

        locationResponse.setExtract(infoObject.getString("extract"));
        locationResponse.setFullUrl(infoObject.getString("fullUrl"));

        return locationResponse;
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
        return (keys.length/(float)score < 0.5);
    }

    private String refactorName(LocationResponse locationResponse){

        String[] partialNames = locationResponse.getName().split(" ");

        String name = locationResponse.getName();

        if(partialNames.length > 1 && locationResponse.getName().contains(locationResponse.getVicinity())){
            return name.replace(locationResponse.getVicinity(), "");
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
