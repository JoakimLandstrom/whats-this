package se.jola.whatsthis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.externalapis.impl.GoogleGeoApi;
import se.jola.whatsthis.externalapis.impl.WikiInfoApi;
import se.jola.whatsthis.models.ExceptionModel;
import se.jola.whatsthis.models.Location;

import javax.ws.rs.core.Response;
import java.util.List;

@Component
public final class LocationService {

    @Autowired
    private GoogleGeoApi geoApi;

    @Autowired
    private WikiInfoApi infoApi;

    public List<Location> getLocations(String lat, String lng) {

        try {
            List<Location> locations = geoApi.getLocations(lat, lng);

            return getLocationsInfo(locations);
        } catch (ApiException e) {
            e.printStackTrace();
            throw new ServiceException(new ExceptionModel("Couldnt fetch geolocation names from lat:" + lat + " and lng:" + lng, Response.Status.INTERNAL_SERVER_ERROR));
        }
    }

    private List<Location> getLocationsInfo(List<Location> locations) throws ServiceException {

        for (Location location : locations) {
            try {
                infoApi.getInfoAboutLocation(location);

            } catch (ApiException e) {
                throw new ServiceException(new ExceptionModel("Couldn't get poi info from:" + location.getName(), Response.Status.INTERNAL_SERVER_ERROR));

            }
        }

        return locations;
    }
}
