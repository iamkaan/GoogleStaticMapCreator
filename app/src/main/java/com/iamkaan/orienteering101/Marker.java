package com.iamkaan.orienteering101;

public class Marker {
    private String iconURL;
    private double lat;
    private double lon;

    public Marker() {
    }

    public Marker setLocation(double lat, double lon) {
        this.lat = lat - 0.0002;
        this.lon = lon;
        return this;
    }

    public Marker setIconURL(String iconURL) {
        this.iconURL = iconURL;
        return this;
    }

    public String getURL() {
        return "&markers=size:mid|icon:" + iconURL + "|" + lat + "," + lon;
    }
}