package com.mfzn.deepuses.bean.request.purchase;

import com.mfzn.deepuses.utils.UserHelper;

public class OrderPurchaseListRequest {
    private String uid;
    private String token;
    private String shopID;
    private String keywords;
    private Long startTime;
    private Long endTime;
    private Integer isCheck;//不搜索传‘’，否则0待审核 1通过 2拒绝
    private Integer isPay;//不搜索传‘’，否则0未付款 1付款中 2已付款
    private Integer isInStore;//不搜索传‘’，否则0待入库 1入库中 2已入库
    private String exportExcel;

    public OrderPurchaseListRequest(){
        this.uid=UserHelper.getUid();
        this.token= UserHelper.getToken();
        this.shopID=UserHelper.getShopId();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIsInStore() {
        return isInStore;
    }

    public void setIsInStore(Integer isInStore) {
        this.isInStore = isInStore;
    }

    public String getExportExcel() {
        return exportExcel;
    }

    public void setExportExcel(String exportExcel) {
        this.exportExcel = exportExcel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }
}
