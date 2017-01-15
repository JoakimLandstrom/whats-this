package se.jola.whatsthis.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.externalapi.GeoApi;
import se.jola.whatsthis.externalapi.InfoApi;

@Component
public class LocationService {

    @Autowired
    private GeoApi geoApi;
    
    @Autowired
    private InfoApi infoApi;

    public String getClosestPois(String lat, String lng) {

	try {
	    return jsonArrayToString(geoApi.getLocationInfo(lat, lng), "names");
	} catch (ApiException e) {
	    throw new ServiceException("Couldnt fetch geolocation names from lat:" + lat + " and lng:" + lng);
	}
    }

    public String getPoiInfo(String name) throws ServiceException {

	try {
	    return infoApi.getInfoAboutLocation(name);
	} catch (ApiException e) {
	    throw new ServiceException("Couldn't get poi info from:" + name);
	}
    }
    
    private String jsonArrayToString(JSONArray jsonArray, String name){
	
	JSONObject jsonObject = new JSONObject();
	
	jsonObject.put(name, jsonArray);

	return jsonObject.toString();
    }
   
}
