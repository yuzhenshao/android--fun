package com.mfzn.deepuses.bean.request.store;

public class OrderStockCheckAddResponse {
    private String orderID;
    private long orderTime;
    private String outNum;
    private String orderMakerUserID;
    private String storeID;
    private String orderGoodsStr;
    private String remark;

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getOrderMakerUserID() {
        return orderMakerUserID;
    }

    public void setOrderMakerUserID(String orderMakerUserID) {
        this.orderMakerUserID = orderMakerUserID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getOrderGoodsStr() {
        return orderGoodsStr;
    }

    public void setOrderGoodsStr(String orderGoodsStr) {
        this.orderGoodsStr = orderGoodsStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
