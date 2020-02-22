package com.mfzn.deepuses.model.khgl;

public class SelectCusModel {
    private String id;
    private String name;

    public SelectCusModel(String id, String name, String level, String pro) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.pro = pro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    private String level;
    private String pro;
}
