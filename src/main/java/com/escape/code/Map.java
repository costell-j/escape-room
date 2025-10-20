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
    /*
     * Creates a new instance of the map
     * @param mapName is the name of the map
     * @param height is the height of the map
     * @param length is the length of the map
     * @param open is if the map is open or not
     */
    public Map(String mapName, int height, int length, boolean open) {
        this.mapName = mapName;
        this.height = height;
        this.length = length;
        this.open = open;
    }

    // Getters
    /*
     * returns the map name
     */
    public String getMapName() {
        return this.mapName;
    }
    /*
     * Returns the height of the map
     */
    public int getHeight() {
        return this.height;
    }
    /*
     * returns the length of the map
     */
    public int getLength() {
        return this.length;
    }
    /*
     * returns if the map is open or not
     */
    public boolean isOpen() {
        return open;
    }

    // Setters
    /*
     * Sets whether or not the map is open
     * @param open will be true or false based on whether the map is open or not
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

}
