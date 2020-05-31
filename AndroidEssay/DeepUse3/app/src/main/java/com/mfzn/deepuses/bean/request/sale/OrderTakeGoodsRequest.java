package com.mfzn.deepuses.bean.request.sale;


public class OrderTakeGoodsRequest {

    private String takerUserID;
    private String storeID;
    private String outNum;
    private long orderTime;
    private String orderMakerUserID;
    private String orderGoodsStr;

    private String recName;
    private String recPhone;
    private String recAreaID;
    private String recAddress;
    private String recCode;
    private String recTelephone;
    private String remark;

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMakerUserID() {
        return orderMakerUserID;
    }

    public void setOrderMakerUserID(String orderMakerUserID) {
        this.orderMakerUserID = orderMakerUserID;
    }


    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getRecAreaID() {
        return recAreaID;
    }

    public void setRecAreaID(String recAreaID) {
        this.recAreaID = recAreaID;
    }


    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getRecTelephone() {
        return recTelephone;
    }

    public void setRecTelephone(String recTelephone) {
        this.recTelephone = recTelephone;
    }

    public String getOrderGoodsStr() {
        return orderGoodsStr;
    }

    public void setOrderGoodsStr(String orderGoodsStr) {
        this.orderGoodsStr = orderGoodsStr;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getTakerUserID() {
        return takerUserID;
    }

    public void setTakerUserID(String takerUserID) {
        this.takerUserID = takerUserID;
    }
}
