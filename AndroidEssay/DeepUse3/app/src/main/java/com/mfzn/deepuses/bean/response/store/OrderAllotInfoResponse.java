package com.mfzn.deepuses.bean.response.store;

import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.util.List;

public class OrderAllotInfoResponse {


    /**
     * orderID : 2
     * companyID : 2
     * shopID : 1
     * orderTime : 1585830602
     * orderMakerUserID : 1
     * orderMakerUserName : ewenXing
     * orderNum : RWIJ_DB_00000001
     * outNum : WB0001
     * fromStoreID : 1
     * fromStoreName : 总仓
     * toStoreID : 2
     * toStoreName : 分仓1
     * remark : 这是备注
     * status : 1
     * isCheck : 1
     * checkTime : 1585833207
     * checkNote : 通过
     * checkUserID : 1
     * checkUserName : ewenXing
     * addTime : 1585831647
     * goodsInfo : [{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg"}]
     * goodsAllCount : 10
     */

    private String orderID;
    private String companyID;
    private String shopID;
    private long orderTime;
    private String orderMakerUserID;
    private String orderMakerUserName;
    private String orderNum;
    private String outNum;
    private String fromStoreID;
    private String fromStoreName;
    private String toStoreID;
    private String toStoreName;
    private String remark;
    private int status;
    private int isCheck;
    private long checkTime;
    private String checkNote;
    private String checkUserID;
    private String checkUserName;
    private long addTime;
    private int goodsAllCount;
    private List<GoodsInfoResponse> goodsInfo;

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

    public String getFromStoreName() {
        return fromStoreName;
    }

    public void setFromStoreName(String fromStoreName) {
        this.fromStoreName = fromStoreName;
    }

    public String getToStoreID() {
        return toStoreID;
    }

    public void setToStoreID(String toStoreID) {
        this.toStoreID = toStoreID;
    }

    public String getToStoreName() {
        return toStoreName;
    }

    public void setToStoreName(String toStoreName) {
        this.toStoreName = toStoreName;
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

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckNote() {
        return checkNote;
    }

    public void setCheckNote(String checkNote) {
        this.checkNote = checkNote;
    }

    public String getCheckUserID() {
        return checkUserID;
    }

    public void setCheckUserID(String checkUserID) {
        this.checkUserID = checkUserID;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getGoodsAllCount() {
        return goodsAllCount;
    }

    public void setGoodsAllCount(int goodsAllCount) {
        this.goodsAllCount = goodsAllCount;
    }

    public List<GoodsInfoResponse> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}
