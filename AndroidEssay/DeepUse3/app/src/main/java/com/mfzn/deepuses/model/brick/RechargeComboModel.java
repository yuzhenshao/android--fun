package com.mfzn.deepuses.model.brick;

public class RechargeComboModel {

    /**
     * comboName : 首充88元
     * price : 88.00
     * zhuan : 880
     * giftZhuan : 0
     * onlyOnce : 1
     * isDel : 0
     * addTime : 1576035197
     * updateTime : 0
     * data_id : 1
     * data_en_id : Fh1SQdvX4
     */

    private String comboName;
    private String price;
    private int zhuan;
    private int giftZhuan;
    private int onlyOnce;
    private int isDel;
    private int addTime;
    private int updateTime;
    private int isBuy;
    private int data_id;
    private String data_en_id;
    private boolean clickType = false;

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    public boolean getClickType() {
        return clickType;
    }

    public void setClickType(boolean clickType) {
        this.clickType = clickType;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getZhuan() {
        return zhuan;
    }

    public void setZhuan(int zhuan) {
        this.zhuan = zhuan;
    }

    public int getGiftZhuan() {
        return giftZhuan;
    }

    public void setGiftZhuan(int giftZhuan) {
        this.giftZhuan = giftZhuan;
    }

    public int getOnlyOnce() {
        return onlyOnce;
    }

    public void setOnlyOnce(int onlyOnce) {
        this.onlyOnce = onlyOnce;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getData_en_id() {
        return data_en_id;
    }

    public void setData_en_id(String data_en_id) {
        this.data_en_id = data_en_id;
    }
}
