package com.mfzn.deepuses.bean.request.sale;


public class OrderTakeGoodsBackRequest {

    private String takerUserID;
    private String storeID;
    private String outNum;
    private long orderTime;
    private String orderMakerUserID;
    private String orderGoodsStr;
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
