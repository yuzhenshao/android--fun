package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class WaitingOutDetailResponse {


    /**
     * orderInfo : {"orderID":1,"orderNum":"RWIJ_XS_00000001","orderTime":1587192681,"recName":"小王","recPhone":"15300000000","recAreaID":"320412","recAddress":"安通大厦","recCode":"000000","recTelephone":"0519-000000","orderTypeName":"销售单","fullAddress":"江苏省常州市武进区安通大厦"}
     * goodsInfo : [{"detailID":8,"dataID":8,"storeID":1,"storeName":"总仓","goodsID":1,"goodsName":"MacBook Pro","goodsNum":"SP0001","goodsCatID":4,"catName":"Apple2","goodsAttr":"最新的","goodsBarCode":"111111","goodsMainImage":"1.jpg","goodsUnitID":3,"unitName":"台","shouldDone":10,"hasDone":0,"needDone":10,"status":0,"addTime":1587195188,"goodsSumStock":300}]
     */

    private OrderInfoResponse orderInfo;
    private List<WaitingInDetailResponse> goodsInfo;

    public OrderInfoResponse getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoResponse orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<WaitingInDetailResponse> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<WaitingInDetailResponse> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public static class OrderInfoResponse {
        /**
         * orderID : 1
         * orderNum : RWIJ_XS_00000001
         * orderTime : 1587192681
         * recName : 小王
         * recPhone : 15300000000
         * recAreaID : 320412
         * recAddress : 安通大厦
         * recCode : 000000
         * recTelephone : 0519-000000
         * orderTypeName : 销售单
         * fullAddress : 江苏省常州市武进区安通大厦
         */

        private int orderID;
        private String orderNum;
        private int orderTime;
        private String recName;
        private String recPhone;
        private String recAreaID;
        private String recAddress;
        private String recCode;
        private String recTelephone;
        private String orderTypeName;
        private String fullAddress;

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public int getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(int orderTime) {
            this.orderTime = orderTime;
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

        public String getOrderTypeName() {
            return orderTypeName;
        }

        public void setOrderTypeName(String orderTypeName) {
            this.orderTypeName = orderTypeName;
        }

        public String getFullAddress() {
            return fullAddress;
        }

        public void setFullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
        }
    }

    public static class GoodsInfoBean {
        /**
         * detailID : 8
         * dataID : 8
         * storeID : 1
         * storeName : 总仓
         * goodsID : 1
         * goodsName : MacBook Pro
         * goodsNum : SP0001
         * goodsCatID : 4
         * catName : Apple2
         * goodsAttr : 最新的
         * goodsBarCode : 111111
         * goodsMainImage : 1.jpg
         * goodsUnitID : 3
         * unitName : 台
         * shouldDone : 10
         * hasDone : 0
         * needDone : 10
         * status : 0
         * addTime : 1587195188
         * goodsSumStock : 300
         */

        private int detailID;
        private int dataID;
        private int storeID;
        private String storeName;
        private int goodsID;
        private String goodsName;
        private String goodsNum;
        private int goodsCatID;
        private String catName;
        private String goodsAttr;
        private String goodsBarCode;
        private String goodsMainImage;
        private int goodsUnitID;
        private String unitName;
        private int shouldDone;
        private int hasDone;
        private int needDone;
        private int status;
        private int addTime;
        private int goodsSumStock;

        public int getDetailID() {
            return detailID;
        }

        public void setDetailID(int detailID) {
            this.detailID = detailID;
        }

        public int getDataID() {
            return dataID;
        }

        public void setDataID(int dataID) {
            this.dataID = dataID;
        }

        public int getStoreID() {
            return storeID;
        }

        public void setStoreID(int storeID) {
            this.storeID = storeID;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(int goodsID) {
            this.goodsID = goodsID;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getGoodsCatID() {
            return goodsCatID;
        }

        public void setGoodsCatID(int goodsCatID) {
            this.goodsCatID = goodsCatID;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getGoodsAttr() {
            return goodsAttr;
        }

        public void setGoodsAttr(String goodsAttr) {
            this.goodsAttr = goodsAttr;
        }

        public String getGoodsBarCode() {
            return goodsBarCode;
        }

        public void setGoodsBarCode(String goodsBarCode) {
            this.goodsBarCode = goodsBarCode;
        }

        public String getGoodsMainImage() {
            return goodsMainImage;
        }

        public void setGoodsMainImage(String goodsMainImage) {
            this.goodsMainImage = goodsMainImage;
        }

        public int getGoodsUnitID() {
            return goodsUnitID;
        }

        public void setGoodsUnitID(int goodsUnitID) {
            this.goodsUnitID = goodsUnitID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public int getShouldDone() {
            return shouldDone;
        }

        public void setShouldDone(int shouldDone) {
            this.shouldDone = shouldDone;
        }

        public int getHasDone() {
            return hasDone;
        }

        public void setHasDone(int hasDone) {
            this.hasDone = hasDone;
        }

        public int getNeedDone() {
            return needDone;
        }

        public void setNeedDone(int needDone) {
            this.needDone = needDone;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getGoodsSumStock() {
            return goodsSumStock;
        }

        public void setGoodsSumStock(int goodsSumStock) {
            this.goodsSumStock = goodsSumStock;
        }
    }
}
