package com.iamkaan.orienteering101;

public class MapCreator {
    private String BASE_URL = "http://maps.google.com/maps/api/staticmap?";
    private String center;
    private String zoom;
    private String size;
    private String style;
    private String finalURL;
    private String markers = "";

    public MapCreator() {
        setStyle();
    }

    public MapCreator setCenter(double lat, double lon) {
        this.center = "center=" + lat + "," + lon;
        return this;
    }

    public MapCreator setZoom(int zoom) {
        this.zoom = "zoom=" + zoom;
        return this;
    }

    public MapCreator setSize(int height, int width) {
        this.size = "size=" + height + "x" + width;
        return this;
    }

    public MapCreator addMarker(Marker marker) {
        markers += marker.getURL();
        return this;
    }

    public String create() {
        finalURL = BASE_URL + center + "&" + zoom + "&" + size + "&" + style + markers;
        return finalURL;
    }

    private void setStyle() {
        style = "format=png" +
                "&" +
                "sensor=false" +
                "&" +
                "maptype=roadmap" +
                "&" +
                "style=feature:landscape|visibility:on|color:0x333333" +
                "&" +
                "style=feature:road|visibility:on|color:0x2c6570|weight:1" +
                "&" +
                "style=feature:road|element:labels|visibility:off" +
                "&" +
                "style=feature:poi|visibility:off" +
                "&" +
                "style=feature:transit|visibility:off" +
                "&" +
                "style=feature:administrative|visibility:off";
    }
}