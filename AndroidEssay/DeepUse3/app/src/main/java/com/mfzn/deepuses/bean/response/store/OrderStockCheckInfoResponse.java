package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class OrderStockCheckInfoResponse {


    /**
     * orderID : 1
     * companyID : 2
     * shopID : 1
     * orderTime : 1586442493
     * orderMakerUserID : 1
     * orderMakerUserName : ewenXing
     * orderNum : RWIJ_PD_00000001
     * outNum : wb001
     * storeID : 1
     * storeName : 总仓
     * remark : 老鼠咬坏了一个
     * isCheck : 1
     * checkTime : 1586443293
     * checkUserID : 1
     * status : 3
     * checkUserName : ewenXing
     * checkNote : 通过
     * addTime : 1586442775
     * statusText : 盈亏处理完成
     * goodsInfo : [{"goodsID":1,"goodsCount":0,"goodsName":"MacBook Pro","goodsCatName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","systemStockNum":100,"checkStockNum":99,"checkChangeNum":-1,"remark":""}]
     * systemSumCount : 100
     * checkSumCount : 99
     */

    private String orderID;
    private String companyID;
    private String shopID;
    private long orderTime;
    private String orderMakerUserID;
    private String orderMakerUserName;
    private String orderNum;
    private String outNum;
    private String storeID;
    private String storeName;
    private String remark;
    private int isCheck;
    private String checkTime;
    private String checkUserID;
    private int status;
    private String checkUserName;
    private String checkNote;
    private long addTime;
    private String statusText;
    private int systemSumCount;
    private int checkSumCount;
    private List<GoodsInfoBean> goodsInfo;

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

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
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

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getSystemSumCount() {
        return systemSumCount;
    }

    public void setSystemSumCount(int systemSumCount) {
        this.systemSumCount = systemSumCount;
    }

    public int getCheckSumCount() {
        return checkSumCount;
    }

    public void setCheckSumCount(int checkSumCount) {
        this.checkSumCount = checkSumCount;
    }

    public List<GoodsInfoBean> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public static class GoodsInfoBean {
        /**
         * goodsID : 1
         * goodsCount : 0
         * goodsName : MacBook Pro
         * goodsCatName : Apple
         * goodsAttr : 最新的
         * goodsNum : SP0001
         * goodsUnitName : 台
         * goodsBarCode : 111111
         * goodsBrand : Apple
         * goodsPosition : 1
         * goodsMainImage : 1.jpg
         * systemStockNum : 100
         * checkStockNum : 99
         * checkChangeNum : -1
         * remark :
         */

        private String goodsID;
        private int goodsCount;
        private String goodsName;
        private String goodsCatName;
        private String goodsAttr;
        private String goodsNum;
        private String goodsUnitName;
        private String goodsBarCode;
        private String goodsBrand;
        private int goodsPosition;
        private String goodsMainImage;
        private int systemStockNum;
        private int checkStockNum;
        private int checkChangeNum;
        private String remark;

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsCatName() {
            return goodsCatName;
        }

        public void setGoodsCatName(String goodsCatName) {
            this.goodsCatName = goodsCatName;
        }

        public String getGoodsAttr() {
            return goodsAttr;
        }

        public void setGoodsAttr(String goodsAttr) {
            this.goodsAttr = goodsAttr;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsUnitName() {
            return goodsUnitName;
        }

        public void setGoodsUnitName(String goodsUnitName) {
            this.goodsUnitName = goodsUnitName;
        }

        public String getGoodsBarCode() {
            return goodsBarCode;
        }

        public void setGoodsBarCode(String goodsBarCode) {
            this.goodsBarCode = goodsBarCode;
        }

        public String getGoodsBrand() {
            return goodsBrand;
        }

        public void setGoodsBrand(String goodsBrand) {
            this.goodsBrand = goodsBrand;
        }

        public int getGoodsPosition() {
            return goodsPosition;
        }

        public void setGoodsPosition(int goodsPosition) {
            this.goodsPosition = goodsPosition;
        }

        public String getGoodsMainImage() {
            return goodsMainImage;
        }

        public void setGoodsMainImage(String goodsMainImage) {
            this.goodsMainImage = goodsMainImage;
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

        public int getCheckChangeNum() {
            return checkChangeNum;
        }

        public void setCheckChangeNum(int checkChangeNum) {
            this.checkChangeNum = checkChangeNum;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
