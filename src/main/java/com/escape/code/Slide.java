package com.escape.code;

public class Slide {
    
    private String description;
    private String imagePath;

    public Slide(String description, String imagePath) {
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
}
