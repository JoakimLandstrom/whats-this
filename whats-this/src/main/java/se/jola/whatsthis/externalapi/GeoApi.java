package se.jola.whatsthis.externalapi;

import java.util.List;

import se.jola.whatsthis.exceptions.ApiException;

public interface GeoApi {

    List<String> getLocationInfo(String lat, String lng) throws ApiException;
}
