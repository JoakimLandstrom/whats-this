package se.jola.whatsthis.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.externalapis.impl.GoogleGeoApi;
import se.jola.whatsthis.externalapis.impl.WikiInfoApi;
import se.jola.whatsthis.models.ExceptionModel;

import javax.ws.rs.core.Response;

@Component
public final class LocationService {

    @Autowired
    private GoogleGeoApi geoApi;

    @Autowired
    private WikiInfoApi infoApi;

    public String getClosestPois(String lat, String lng) {

        try {
            return jsonArrayToString(geoApi.getLocationInfo(lat, lng), "names");
        } catch (ApiException e) {
            throw new ServiceException(new ExceptionModel("Couldnt fetch geolocation names from lat:" + lat + " and lng:" + lng, Response.Status.INTERNAL_SERVER_ERROR  ));
        }
    }

    public String getPoiInfo(String name) throws ServiceException {

        try {
            return infoApi.getInfoAboutLocation(name);
        } catch (ApiException e) {
            throw new ServiceException(new ExceptionModel("Couldn't get poi info from:" + name, Response.Status.INTERNAL_SERVER_ERROR));
        }
    }

    private String jsonArrayToString(JSONArray jsonArray, String name) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put(name, jsonArray);

        return jsonObject.toString();
    }
}
