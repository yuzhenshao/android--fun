package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class GoodsStockResponse {

    /**
     * sumStockNum : 490
     * list : {"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"goodsID":1,"goodsName":"MacBook Pro","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsMainImage":"1.jpg","storeStockNum":[{"storeID":1,"storeName":"总仓","stockNum":300},{"storeID":2,"storeName":"分仓1","stockNum":90}],"sumStockNum":390},{"goodsID":2,"goodsName":"MacBook","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsMainImage":"1.jpg","storeStockNum":[{"storeID":1,"storeName":"总仓","stockNum":100},{"storeID":2,"storeName":"分仓1","stockNum":0}],"sumStockNum":100}]}
     */

    private int sumStockNum;
    private ListBean list;

    public int getSumStockNum() {
        return sumStockNum;
    }

    public void setSumStockNum(int sumStockNum) {
        this.sumStockNum = sumStockNum;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * total : 2
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"goodsID":1,"goodsName":"MacBook Pro","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsMainImage":"1.jpg","storeStockNum":[{"storeID":1,"storeName":"总仓","stockNum":300},{"storeID":2,"storeName":"分仓1","stockNum":90}],"sumStockNum":390},{"goodsID":2,"goodsName":"MacBook","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsMainImage":"1.jpg","storeStockNum":[{"storeID":1,"storeName":"总仓","stockNum":100},{"storeID":2,"storeName":"分仓1","stockNum":0}],"sumStockNum":100}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<StockResponse> data;

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

        public List<StockResponse> getData() {
            return data;
        }

        public void setData(List<StockResponse> data) {
            this.data = data;
        }

        public static class StockResponse {
            /**
             * goodsID : 1
             * goodsName : MacBook Pro
             * goodsCatID : 4
             * catName : Apple
             * goodsAttr : 最新的
             * goodsNum : SP0001
             * goodsUnitID : 3
             * unitName : 台
             * goodsBarCode : 111111
             * goodsBrand : Apple
             * goodsMainImage : 1.jpg
             * storeStockNum : [{"storeID":1,"storeName":"总仓","stockNum":300},{"storeID":2,"storeName":"分仓1","stockNum":90}]
             * sumStockNum : 390
             */

            private String goodsID;
            private String goodsName;
            private String goodsCatID;
            private String catName;
            private String goodsAttr;
            private String goodsNum;
            private String goodsUnitID;
            private String unitName;
            private String goodsBarCode;
            private String goodsBrand;
            private String goodsMainImage;
            private int sumStockNum;
            private List<StoreStockNumBean> storeStockNum;

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

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
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

            public int getSumStockNum() {
                return sumStockNum;
            }

            public void setSumStockNum(int sumStockNum) {
                this.sumStockNum = sumStockNum;
            }

            public List<StoreStockNumBean> getStoreStockNum() {
                return storeStockNum;
            }

            public void setStoreStockNum(List<StoreStockNumBean> storeStockNum) {
                this.storeStockNum = storeStockNum;
            }

            public static class StoreStockNumBean {
                /**
                 * storeID : 1
                 * storeName : 总仓
                 * stockNum : 300
                 */

                private String storeID;
                private String storeName;
                private int stockNum;

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
            }
        }
    }
}
