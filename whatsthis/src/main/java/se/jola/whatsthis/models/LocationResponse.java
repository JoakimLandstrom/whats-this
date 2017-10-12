package se.jola.whatsthis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class LocationResponse {

    private String name;

    private String vicinity;

    private String extract;

    private String fullUrl;

    private String picLink;

    private LocationRequest locationRequest;

    private Geometry geometry;

    private int distance;

    public LocationResponse(){

    }

    public LocationResponse(String name, String vicinity){
        this.name = name;
        this.vicinity = vicinity;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getExtract() {
        return extract;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getPicLink() {
        return picLink;
    }

    public int getDistance() {
        return distance;
    }

    public LocationRequest getLocationRequest() {
        return locationRequest;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationRequest(LocationRequest locationRequest) {
        this.locationRequest = locationRequest;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
