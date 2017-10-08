package se.jola.whatsthis.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class PositionInfoResponse {

    @JsonProperty("results")
    private List<Location> locationList;

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> location) {
        this.locationList = location;
    }
}
