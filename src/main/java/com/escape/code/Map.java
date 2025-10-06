package com.escape.code;
/*
 * Class used as the map
 * @Author Erin Check
 */
public class Map {
    
    private String mapName;
    private int height;
    private int length;

    public Map(String mapName, int height, int length) {
        this.mapName = mapName;
        this.height = height;
        this.length = length;
    }

    public String getMapName() {
        return this.mapName;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLength() {
        return this.length;
    }

}
