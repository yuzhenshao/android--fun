package com.mfzn.deepuses.bean.response;

/**
 * @author yz @date 2020-03-30
 */
public class GoodsUnitResponse {

    /**
     * goodsUnitID : 1
     * unitName : 件
     * addTime : 1583762564
     */

    private String goodsUnitID;
    private String unitName;
    private int addTime;
    private boolean isSelected;

    public String getGoodsUnitID() {
        return goodsUnitID;
    }

    public void setGoodsUnitID(String goodsUnitID) {
        this.goodsUnitID = goodsUnitID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
