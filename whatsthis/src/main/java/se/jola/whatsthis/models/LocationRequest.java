package se.jola.whatsthis.models;

import javax.ws.rs.QueryParam;

public final class LocationRequest {

    @QueryParam("lng")
    private String lng;

    @QueryParam("lat")
    private String lat;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

}
