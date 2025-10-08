package com.escape.code;
/*
 * Class used as the map
 * @Author Erin Check
 */
public class Map {
    
    private String mapName;
    private int height;
    private int length;
    private boolean open;

    public Map(String mapName, int height, int length, boolean open) {
        this.mapName = mapName;
        this.height = height;
        this.length = length;
        this.open = open;
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

    public boolean isOpen() {
        return this.open;
    }

}
