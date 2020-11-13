package com.mfzn.deepuses.bean.response.store;

import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.util.List;

public class OtherWaitingInOutDetailResponse {


    /**
     * orderID : 3
     * orderType : 9
     * companyID : 2
     * shopID : 1
     * supplierID : 0
     * customerID : 1
     * orderTime : 1585377154
     * orderMakerUserID : 1
     * orderMakerUserName : ewenXing
     * orderNum : RWIJ_QTCK_00000003
     * totalMoney : 182.00
     * discountAmount : 2.00
     * realMoney : 180.00
     * remark : 这个是备注
     * recName : 小王
     * recPhone : 15300000000
     * recAreaID : 320412
     * provinceName : 江苏省
     * cityName : 常州市
     * areaName : 武进区
     * recAddress : 安通大厦
     * recCode : 000000
     * recTelephone : 0519-0000000
     * isCheck : 1
     * checkTime : 1585378718
     * checkUserID : 1
     * status : 1
     * checkUserName : ewenXing
     * checkNote :
     * addTime : 1585377268
     * receiverInfo : {"receiverID":1,"receiverName":"ewenXing","receiverType":1}
     * statusText : 已通过待完成
     * goodsInfo : [{"goodsID":1,"goodsCount":2,"goodsName":"MacBook Pro","goodsCatName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","uniformSalePrice":"100.00","price":"90.00","priceWithTax":"91.00","taxRate":"0.10","money":"182.00"}]
     */

    private String orderID;
    private int orderType;
    private String companyID;
    private String shopID;
    private String supplierID;
    private String customerID;
    private long orderTime;
    private String orderMakerUserID;
    private String orderMakerUserName;
    private String orderNum;
    private String totalMoney;
    private String discountAmount;
    private String realMoney;
    private String remark;
    private String recName;
    private String recPhone;
    private String recAreaID;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String recAddress;
    private String recCode;
    private String recTelephone;
    private int isCheck;//0待审核 1通过 2拒绝
    private long checkTime;
    private String checkUserID;
    private int status;//"status": 1,当前状态：0.待审核；1.审核通过，操作中；2.审核拒绝；3.已完成 <number>
    private String checkUserName;
    private String checkNote;
    private long addTime;
    private ReceiverInfoResponse receiverInfo;
    private String statusText;
    private List<GoodsInfoResponse> goodsInfo;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getCheckUserID() {
        return checkUserID;
    }

    public void setCheckUserID(String checkUserID) {
        this.checkUserID = checkUserID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public String getCheckNote() {
        return checkNote;
    }

    public void setCheckNote(String checkNote) {
        this.checkNote = checkNote;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public ReceiverInfoResponse getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ReceiverInfoResponse receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<GoodsInfoResponse> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public static class ReceiverInfoResponse {
        /**
         * receiverID : 1
         * receiverName : ewenXing
         * receiverType : 1
         */

        private String receiverID;
        private String receiverName;
        private int receiverType;

        public String getReceiverID() {
            return receiverID;
        }

        public void setReceiverID(String receiverID) {
            this.receiverID = receiverID;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public int getReceiverType() {
            return receiverType;
        }

        public void setReceiverType(int receiverType) {
            this.receiverType = receiverType;
        }
    }
}
