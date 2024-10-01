package com.example.recyclerviewdemo;

public class LandMark {
    private int imageResId;
    private String name;

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LandMark(int imageResId, String name){
        this.imageResId = imageResId;
        this.name = name;

    }
}
