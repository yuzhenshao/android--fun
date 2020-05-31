package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class StockWarningResponse {

    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"data_id":4,"goodsID":2,"goodsName":"MacBook Air","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsMainImage":"1.jpg","storeID":2,"storeName":"分仓1","stockNum":9,"warningMin":10,"warningMax":0}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<StockWarning> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<StockWarning> getData() {
        return data;
    }

    public void setData(List<StockWarning> data) {
        this.data = data;
    }

    public static class StockWarning {
        /**
         * data_id : 4
         * goodsID : 2
         * goodsName : MacBook Air
         * goodsCatID : 4
         * catName : Apple
         * goodsAttr : 最新的
         * goodsNum : SP0002
         * goodsUnitID : 3
         * unitName : 台
         * goodsBarCode : 111111
         * goodsBrand : Apple
         * goodsMainImage : 1.jpg
         * storeID : 2
         * storeName : 分仓1
         * stockNum : 9
         * warningMin : 10
         * warningMax : 0
         */

        private String data_id;
        private String goodsID;
        private String goodsName;
        private String goodsCatID;
        private String catName;
        private String goodsAttr;
        private int goodsNum;
        private int goodsUnitID;
        private String unitName;
        private String goodsBarCode;
        private String goodsBrand;
        private String goodsMainImage;
        private String storeID;
        private String storeName;
        private int stockNum;
        private int warningMin;
        private int warningMax;

        public String getData_id() {
            return data_id;
        }

        public void setData_id(String data_id) {
            this.data_id = data_id;
        }

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsCatID() {
            return goodsCatID;
        }

        public void setGoodsCatID(String goodsCatID) {
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

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
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

        public String getGoodsMainImage() {
            return goodsMainImage;
        }

        public void setGoodsMainImage(String goodsMainImage) {
            this.goodsMainImage = goodsMainImage;
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

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public int getWarningMin() {
            return warningMin;
        }

        public void setWarningMin(int warningMin) {
            this.warningMin = warningMin;
        }

        public int getWarningMax() {
            return warningMax;
        }

        public void setWarningMax(int warningMax) {
            this.warningMax = warningMax;
        }
    }
}
