package com.mfzn.deepuses.bean.response.store;

public class StoreCheckResponse {

    /**
     * orderID : 1
     * companyID : 2
     * shopID : 1
     * startTime : 1586448000
     * endTime : 1586634400
     * orderMakerUserID : 1
     * orderMakerUserName : ewenXing
     * orderNum : RWIJ_QKPD_00000001
     * storeID : 1
     * storeName : 总仓
     * remark : 自动创建
     * status : 2
     * addTime : 1586594721
     * statusText : 待盈亏处理
     * systemStockNum : 99
     * checkStockNum : 100
     */

    private String orderID;
    private String companyID;
    private String shopID;
    private long startTime;
    private long endTime;
    private String orderMakerUserID;
    private String orderMakerUserName;
    private String orderNum;
    private String storeID;
    private String storeName;
    private String remark;
    private int status;
    private long addTime;
    private String statusText;
    private int systemStockNum;
    private int checkStockNum;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getOrderMakerUserID() {
        return orderMakerUserID;
    }

    public void setOrderMakerUserID(String orderMakerUserID) {
        this.orderMakerUserID = orderMakerUserID;
    }

    public String getOrderMakerUserName() {
        return orderMakerUserName;
    }

    public void setOrderMakerUserName(String orderMakerUserName) {
        this.orderMakerUserName = orderMakerUserName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getSystemStockNum() {
        return systemStockNum;
    }

    public void setSystemStockNum(int systemStockNum) {
        this.systemStockNum = systemStockNum;
    }

    public int getCheckStockNum() {
        return checkStockNum;
    }

    public void setCheckStockNum(int checkStockNum) {
        this.checkStockNum = checkStockNum;
    }
}
