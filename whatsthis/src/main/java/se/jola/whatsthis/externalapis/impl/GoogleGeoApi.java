package se.jola.whatsthis.externalapis.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.models.PositionInfoResponse;
import se.jola.whatsthis.models.LocationResponse;
import se.jola.whatsthis.models.LocationRequest;

import java.io.*;
import java.util.List;
import java.util.Properties;

@Component
public final class GoogleGeoApi {

    private RestTemplate restTemplate = new RestTemplate();

    private String getUrl(LocationRequest locationRequest) throws IOException {
        StringBuilder builder = new StringBuilder();

        return builder.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=").append(locationRequest.getLat()).append(",")
                .append(locationRequest.getLng()).append("&type=point_of_interest|locality|natural_feature|place_of_worship|establishment&radius=200" +
                        "&key=").append(getKey()).toString();

    }

    public List<LocationResponse> getLocationsFromRequest(LocationRequest locationRequest) throws ApiException {

        try {
            List<LocationResponse> locationResponses = restTemplate.getForObject(getUrl(locationRequest), PositionInfoResponse.class).getLocationResponseList();
            return locationResponses;
        } catch (RestClientException | IOException e) {
            throw new ApiException("Couldnt get info from lat:" + locationRequest.getLat() + "and lng:" + locationRequest.getLng() + " from google");
        }
    }

    private String getKey() throws IOException {

        Properties properties = new Properties();

        InputStream inputStream = new FileInputStream("src/main/resources/application.properties");

        properties.load(inputStream);

        return properties.getProperty("key");
    }

}
