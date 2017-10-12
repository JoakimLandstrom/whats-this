package se.jola.whatsthis.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class PositionInfoResponse {

    @JsonProperty("results")
    private List<LocationResponse> locationResponseList;

    public List<LocationResponse> getLocationResponseList() {
        return locationResponseList;
    }

    public void setLocationResponseList(List<LocationResponse> locationResponse) {
        this.locationResponseList = locationResponse;
    }
}
