package se.jola.whatsthis.externalapi;

import org.json.JSONArray;

import se.jola.whatsthis.exceptions.ApiException;

public interface GeoApi {

    JSONArray getLocationInfo(String lat, String lng) throws ApiException;
}
