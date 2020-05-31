package com.mfzn.deepuses.bean.request.store;

public class OrderAllotAddRequest {
    private long orderTime;
    private String outNum;
    private String orderMakerUserID;
    private String fromStoreID;
    private String toStoreID;
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

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getFromStoreID() {
        return fromStoreID;
    }

    public void setFromStoreID(String fromStoreID) {
        this.fromStoreID = fromStoreID;
    }

    public String getToStoreID() {
        return toStoreID;
    }

    public void setToStoreID(String toStoreID) {
        this.toStoreID = toStoreID;
    }
}
