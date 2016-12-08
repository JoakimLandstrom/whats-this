package se.jola.whatsthis.service;

import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.externalapi.GeoApi;
import se.jola.whatsthis.externalapi.InfoApi;

public class GeoService {

    private final GeoApi geoApi;
    private final InfoApi infoApi;

    public GeoService(GeoApi geoRepository, InfoApi infoApi) {
	this.geoApi = geoRepository;
	this.infoApi = infoApi;
    }

    public String getClosestPois(String lat, String lng) {

	try {
	    return geoApi.getLocationInfo(lat, lng).toString();
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
}
