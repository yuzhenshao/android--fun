package com.mfzn.deepuses.bean.response.settings;

import java.io.Serializable;

/**
 * @author yz @date 2020-05-04
 */
public class SalesRecordResponse implements Serializable {
    /**
     * companyID : 2
     * orderTime : 1587192681
     * orderNum : RWIJ_XS_00000002
     * customerID : 6
     * salesPersonUserID : 1
     * salesPersonUserName : ewenXing
     * shopID : 1
     * shopName : 总门店
     * goodsCount : 10
     * money : 10010.00
     * profitMoney : 0.00
     * orderID : 2
     * customerName : 李四四
     * showSalePrice : 1001.00
     */

    private int companyID;
    private int orderTime;
    private String orderNum;
    private int customerID;
    private int salesPersonUserID;
    private String salesPersonUserName;
    private int shopID;
    private String shopName;
    private int goodsCount;
    private String money;
    private String profitMoney;
    private int orderID;
    private String customerName;
    private String showSalePrice;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSalesPersonUserID() {
        return salesPersonUserID;
    }

    public void setSalesPersonUserID(int salesPersonUserID) {
        this.salesPersonUserID = salesPersonUserID;
    }

    public String getSalesPersonUserName() {
        return salesPersonUserName;
    }

    public void setSalesPersonUserName(String salesPersonUserName) {
        this.salesPersonUserName = salesPersonUserName;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProfitMoney() {
        return profitMoney;
    }

    public void setProfitMoney(String profitMoney) {
        this.profitMoney = profitMoney;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShowSalePrice() {
        return showSalePrice;
    }

    public void setShowSalePrice(String showSalePrice) {
        this.showSalePrice = showSalePrice;
    }
}
