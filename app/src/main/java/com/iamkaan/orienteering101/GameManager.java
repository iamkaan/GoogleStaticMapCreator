package com.iamkaan.orienteering101;

/**
 * Created by kaanmamikoglu on 24/05/15.
 */
public class GameManager {
    private String baseUrl = "http://maps.google.com/maps/api/staticmap?";
    private String zoom = "16";
    private String miniMapSize;
    private String style;
    private String markers = "";
    private String borders = "";

    public GameManager(double centerLat, double centerLng) {
        setStyle();
        drawBorders(centerLat, centerLng);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getZoom() {
        return zoom;
    }

    public String getStyle() {
        return style;
    }

    public String getMarkers() {
        return markers;
    }

    public String getMiniMapSize() {
        return miniMapSize;
    }

    public String getBorders() {
        return borders;
    }

    public void addMarker(Marker marker) {
        markers += marker.getURL();
    }

    public void setZoom(int zoom) {
        this.zoom = "zoom=" + zoom;
    }

    public void setMiniMapSize(int height, int width) {
        this.miniMapSize = "size=" + height + "x" + width;
    }

    private void drawBorders(double centerLat, double centerLng) {
        double cornerTopLeftLat = centerLat + 0.0075;
        double cornerTopLeftLng = centerLng - 0.0075;
        double cornerTopRightLat = centerLat + 0.0075;
        double cornerTopRightLng = centerLng + 0.0075;
        double cornerBottomRightLat = centerLat - 0.0075;
        double cornerBottomRightLng = centerLng + 0.0075;
        double cornerBottomLeftLat = centerLat - 0.0075;
        double cornerBottomLeftLng = centerLng - 0.0075;
        borders = "&path=color:0x00000000|weight:5|fillcolor:0xcc000077|" +
                String.valueOf(cornerTopLeftLat) + "" + String.valueOf(cornerTopLeftLng) +
                String.valueOf(cornerBottomLeftLat) + "" + String.valueOf(cornerBottomLeftLng) +
                String.valueOf(cornerBottomRightLat) + "" + String.valueOf(cornerBottomRightLng) +
                String.valueOf(cornerTopRightLat) + "" + String.valueOf(cornerTopRightLng) +
                String.valueOf(cornerTopRightLat + 0.1) + "" + String.valueOf(cornerTopRightLng + 0.1) +
                String.valueOf(cornerBottomRightLat - 0.1) + "" + String.valueOf(cornerBottomRightLng + 0.1) +
                String.valueOf(cornerBottomLeftLat - 0.1) + "" + String.valueOf(cornerBottomLeftLng - 0.1) +
                String.valueOf(cornerTopLeftLat + 0.1) + "" + String.valueOf(cornerTopLeftLng - 0.1) +
                String.valueOf(cornerTopRightLat + 0.1) + "" + String.valueOf(cornerTopRightLng + 0.1) +
                String.valueOf(cornerTopRightLat) + "" + String.valueOf(cornerTopRightLng) +
                String.valueOf(cornerTopLeftLat) + "" + String.valueOf(cornerTopLeftLng);
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
