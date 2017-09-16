package se.jola.whatsthis.models;

public final class Location {

    private String name;

    private String address;

    private String information;

    private String infoLink;

    private String picLink;

    public Location(String name, String country){
        this.name = name;
        this.address = country;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation() {
        return information;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }
}
