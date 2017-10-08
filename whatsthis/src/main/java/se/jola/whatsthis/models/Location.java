package se.jola.whatsthis.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Location {

    private String name;

    private String vicinity;

    private String extract;

    private String fullUrl;

    private String picLink;

    public Location(){

    }

    public Location(String name, String vicinity){
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

}
