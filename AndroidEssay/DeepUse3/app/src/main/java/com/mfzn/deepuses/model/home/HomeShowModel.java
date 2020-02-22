package com.mfzn.deepuses.model.home;

public class HomeShowModel {

    private String name;
    private String type;
    private int photo;

    public HomeShowModel(String name, String type, int photo) {
        this.name = name;
        this.type = type;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
