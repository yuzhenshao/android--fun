package com.mfzn.deepuses.bean.response.sale;

import java.util.List;

public class PersonalStoreListResponse {

    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"personalStoreID":1,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","goodsBarCode":"111111","goodsBrand":"Apple","goodsCatID":4,"goodsUnitID":3,"unitName":"台","catName":"Apple","goodsAttr":"最新的","stockNum":70,"takeSumNum":100}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<PersonalStoreResponse> data;

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

    public List<PersonalStoreResponse> getData() {
        return data;
    }

    public void setData(List<PersonalStoreResponse> data) {
        this.data = data;
    }

    public static class PersonalStoreResponse {
        /**
         * personalStoreID : 1
         * goodsID : 1
         * goodsMainImage : 1.jpg
         * goodsName : MacBook Pro
         * goodsNum : SP0001
         * goodsBarCode : 111111
         * goodsBrand : Apple
         * goodsCatID : 4
         * goodsUnitID : 3
         * unitName : 台
         * catName : Apple
         * goodsAttr : 最新的
         * stockNum : 70
         * takeSumNum : 100
         */

        private String personalStoreID;
        private String goodsID;
        private String goodsMainImage;
        private String goodsName;
        private String goodsNum;
        private String goodsBarCode;
        private String goodsBrand;
        private String goodsCatID;
        private String goodsUnitID;
        private String unitName;
        private String catName;
        private String goodsAttr;
        private int stockNum;
        private int takeSumNum;

        public String getPersonalStoreID() {
            return personalStoreID;
        }

        public void setPersonalStoreID(String personalStoreID) {
            this.personalStoreID = personalStoreID;
        }

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
        }

        public String getGoodsMainImage() {
            return goodsMainImage;
        }

        public void setGoodsMainImage(String goodsMainImage) {
            this.goodsMainImage = goodsMainImage;
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

        public String getGoodsCatID() {
            return goodsCatID;
        }

        public void setGoodsCatID(String goodsCatID) {
            this.goodsCatID = goodsCatID;
        }

        public String getGoodsUnitID() {
            return goodsUnitID;
        }

        public void setGoodsUnitID(String goodsUnitID) {
            this.goodsUnitID = goodsUnitID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
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

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public int getTakeSumNum() {
            return takeSumNum;
        }

        public void setTakeSumNum(int takeSumNum) {
            this.takeSumNum = takeSumNum;
        }
    }
}
