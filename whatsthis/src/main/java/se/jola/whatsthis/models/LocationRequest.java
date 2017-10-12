package se.jola.whatsthis.models;


public final class LocationRequest {

    private String lat;

    private String lng;

    public LocationRequest(){

    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
