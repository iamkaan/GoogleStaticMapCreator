package com.iamkaan.orienteering101;

public class MapCreator {
    private String center;
    private GameManager gameManager;

    public MapCreator(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public MapCreator setCenter(double lat, double lon) {
        this.center = "center=" + lat + "," + lon;
        return this;
    }

    public String create() {
        return gameManager.getBaseUrl() + center +
                "&" + gameManager.getZoom() +
                "&" + gameManager.getMiniMapSize() +
                "&" + gameManager.getStyle() +
                gameManager.getMarkers() +
                gameManager.getBorders();
    }
}