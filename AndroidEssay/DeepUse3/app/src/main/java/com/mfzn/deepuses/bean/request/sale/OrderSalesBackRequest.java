package com.mfzn.deepuses.bean.request.sale;


public class OrderSalesBackRequest {

    private String companyCustomerID;
    private String outNum;
    private long orderTime;
    private String orderMakerUserID;
    private String storeID;
    private String proID;
    private String orderGoodsStr;
    private String totalMoney;
    private String discountAmount;
    private String realMoney;
    private String otherCostStr;
    private String remark;

    public String getCompanyCustomerID() {
        return companyCustomerID;
    }

    public void setCompanyCustomerID(String companyCustomerID) {
        this.companyCustomerID = companyCustomerID;
    }

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


    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(String realMoney) {
        this.realMoney = realMoney;
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

    public String getOtherCostStr() {
        return otherCostStr;
    }

    public void setOtherCostStr(String otherCostStr) {
        this.otherCostStr = otherCostStr;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }
}
