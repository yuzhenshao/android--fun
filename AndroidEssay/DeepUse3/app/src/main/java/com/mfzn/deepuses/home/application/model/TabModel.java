package com.mfzn.deepuses.home.application.model;

/**
 * @author yz @date 2020-03-27
 */
public class TabModel {
    private String name;
    private String type;
    private int iconResId;

    public TabModel(String name, String type, int iconResId) {
        this.name = name;
        this.type = type;
        this.iconResId = iconResId;
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

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
