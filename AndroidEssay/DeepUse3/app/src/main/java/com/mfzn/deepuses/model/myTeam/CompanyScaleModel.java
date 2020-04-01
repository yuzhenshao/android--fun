package com.mfzn.deepuses.model.myTeam;

public class CompanyScaleModel {

    /**
     * scale_id : 1
     * scale : 1~10人
     */

    private int scaleID;
    private String scale;

    public int getScale_id() {
        return scaleID;
    }

    public void setScale_id(int scale_id) {
        this.scaleID = scale_id;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
