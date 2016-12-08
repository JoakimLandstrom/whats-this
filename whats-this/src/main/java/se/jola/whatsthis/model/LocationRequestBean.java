package se.jola.whatsthis.model;

import javax.ws.rs.QueryParam;

public class LocationRequestBean {

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
