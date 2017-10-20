package se.jola.whatsthis.services;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.jola.whatsthis.exceptions.ApiException;
import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.externalapis.impl.GoogleGeoApi;
import se.jola.whatsthis.externalapis.impl.WikiInfoApi;
import se.jola.whatsthis.models.LocationResponse;
import se.jola.whatsthis.models.LocationRequest;

import java.util.Comparator;
import java.util.List;

@Component
public final class LocationService {

    @Autowired
    private GoogleGeoApi geoApi;

    @Autowired
    private WikiInfoApi infoApi;

    private GeodeticCalculator geoCalc = new GeodeticCalculator();


    public List<LocationResponse> getLocations(LocationRequest locationRequest) {

        try {
            List<LocationResponse> locationResponses = geoApi.getLocationsFromRequest(locationRequest);
            locationResponses = getLocationsInfo(locationResponses, locationRequest);
            locationResponses.sort(Comparator.comparing(LocationResponse::getDistance));
            locationResponses.removeIf(l -> l.getName().toLowerCase().matches("(.*(\\sab))|((^ab\\s).*)"));
            return locationResponses;
        } catch (ApiException e) {
            throw new ServiceException("Couldnt fetch geolocation names from lat:" + locationRequest.getLat() + " and lng:" + locationRequest.getLng());
        }
    }

    private List<LocationResponse> getLocationsInfo(List<LocationResponse> locationResponses, LocationRequest locationRequest) throws ServiceException {

        for (LocationResponse locationResponse : locationResponses) {
            try {
                locationResponse.setLocationRequest(locationRequest);
                infoApi.getInfoAboutLocationName(locationResponse);
                locationResponse.setDistance((int) countDistance(locationResponse));
            } catch (ApiException e) {
                throw new ServiceException("Couldn't get poi info from:" + locationResponse.getName());
            }
        }
        return locationResponses;
    }

    private double countDistance(LocationResponse response) {

        Ellipsoid reference = Ellipsoid.WGS84;

        GlobalPosition pointA = new GlobalPosition(Double.parseDouble(response.getGeometry().getLocation().getLat()),
                Double.parseDouble(response.getGeometry().getLocation().getLng()), 0.0);

        GlobalPosition userPos = new GlobalPosition(Double.parseDouble(response.getLocationRequest().getLat()),
                Double.parseDouble(response.getLocationRequest().getLng()), 0.0);

        return geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance();

    }
}
