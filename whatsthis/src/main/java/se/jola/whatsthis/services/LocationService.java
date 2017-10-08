package se.jola.whatsthis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.externalapis.impl.GoogleGeoApi;
import se.jola.whatsthis.externalapis.impl.WikiInfoApi;
import se.jola.whatsthis.models.ExceptionModel;
import se.jola.whatsthis.models.Location;
import se.jola.whatsthis.models.LocationRequest;

import javax.ws.rs.core.Response;
import java.util.List;

@Component
public final class LocationService {

    @Autowired
    private GoogleGeoApi geoApi;

    @Autowired
    private WikiInfoApi infoApi;

    public List<Location> getLocations(LocationRequest locationRequest) {

        try {
            //List<Location> locations = geoApi.getLocations(locationRequest.getLat(), locationRequest.getLng());
            List<Location> locations = geoApi.getLocationsFromRequest(locationRequest);
            return getLocationsInfo(locations);
        } catch (ApiException e) {
            e.printStackTrace();
            throw new ServiceException(new ExceptionModel("Couldnt fetch geolocation names from lat:"
                    + locationRequest.getLat() + " and lng:" + locationRequest.getLng(), Response.Status.INTERNAL_SERVER_ERROR));
        }
    }

    private List<Location> getLocationsInfo(List<Location> locations) throws ServiceException {

        for (Location location : locations) {
            try {
                infoApi.getInfoAboutLocationName(location);

            } catch (ApiException e) {
                throw new ServiceException(new ExceptionModel("Couldn't get poi info from:" + location.getName(), Response.Status.INTERNAL_SERVER_ERROR));

            }
        }

        return locations;
    }
}
