package com.mfzn.deepuses.bean.response.store;

import java.util.List;

public class StockLogListResponse {

    /**
     * sumStockNum : 1590
     * list : {"total":33,"per_page":10,"current_page":1,"last_page":4,"data":[{"dataID":41,"goodsID":9,"goodsMainImage":"","goodsName":"iPhone","goodsNum":"iPhone11_001","catName":"","goodsAttr":"iPhone11","num":1000,"nowNum":1000,"inOrOut":1,"inOrOutType":22,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":0,"orderID":0,"addTime":1588228260,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"Excel导入商品入库"},{"dataID":40,"goodsID":8,"goodsMainImage":"","goodsName":"劳力士","goodsNum":"","catName":"","goodsAttr":"","num":100,"nowNum":100,"inOrOut":1,"inOrOutType":22,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":0,"orderID":0,"addTime":1588228260,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"Excel导入商品入库"},{"dataID":34,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":70,"inOrOut":2,"inOrOutType":21,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":13,"orderID":7,"addTime":1587796795,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售退货单作废出库"},{"dataID":33,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":80,"inOrOut":1,"inOrOutType":19,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":13,"orderID":7,"addTime":1587643307,"storeName":"ewenXing的仓库","inOrOutText":"入库","inOrOutTypeText":"零售退货单入库"},{"dataID":32,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":70,"inOrOut":1,"inOrOutType":20,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":6,"addTime":1587635897,"storeName":"ewenXing的仓库","inOrOutText":"入库","inOrOutTypeText":"零售单作废入库"},{"dataID":31,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":60,"inOrOut":2,"inOrOutType":18,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":6,"addTime":1587632109,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售单出库"},{"dataID":30,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":70,"inOrOut":2,"inOrOutType":18,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":5,"addTime":1587632031,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售单出库"},{"dataID":29,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":80,"inOrOut":2,"inOrOutType":18,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":4,"addTime":1587623380,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售单出库"},{"dataID":28,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":90,"nowNum":300,"inOrOut":1,"inOrOutType":13,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":8,"orderID":3,"addTime":1587542953,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"领货归还单入库"},{"dataID":27,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":210,"inOrOut":1,"inOrOutType":13,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":8,"orderID":3,"addTime":1587542891,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"领货归还单入库"}]}
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
         * total : 33
         * per_page : 10
         * current_page : 1
         * last_page : 4
         * data : [{"dataID":41,"goodsID":9,"goodsMainImage":"","goodsName":"iPhone","goodsNum":"iPhone11_001","catName":"","goodsAttr":"iPhone11","num":1000,"nowNum":1000,"inOrOut":1,"inOrOutType":22,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":0,"orderID":0,"addTime":1588228260,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"Excel导入商品入库"},{"dataID":40,"goodsID":8,"goodsMainImage":"","goodsName":"劳力士","goodsNum":"","catName":"","goodsAttr":"","num":100,"nowNum":100,"inOrOut":1,"inOrOutType":22,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":0,"orderID":0,"addTime":1588228260,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"Excel导入商品入库"},{"dataID":34,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":70,"inOrOut":2,"inOrOutType":21,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":13,"orderID":7,"addTime":1587796795,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售退货单作废出库"},{"dataID":33,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":80,"inOrOut":1,"inOrOutType":19,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":13,"orderID":7,"addTime":1587643307,"storeName":"ewenXing的仓库","inOrOutText":"入库","inOrOutTypeText":"零售退货单入库"},{"dataID":32,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":70,"inOrOut":1,"inOrOutType":20,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":6,"addTime":1587635897,"storeName":"ewenXing的仓库","inOrOutText":"入库","inOrOutTypeText":"零售单作废入库"},{"dataID":31,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":60,"inOrOut":2,"inOrOutType":18,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":6,"addTime":1587632109,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售单出库"},{"dataID":30,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":70,"inOrOut":2,"inOrOutType":18,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":5,"addTime":1587632031,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售单出库"},{"dataID":29,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":80,"inOrOut":2,"inOrOutType":18,"storeID":1,"storeType":2,"addUserID":1,"addUserName":"ewenXing","orderType":5,"orderID":4,"addTime":1587623380,"storeName":"ewenXing的仓库","inOrOutText":"出库","inOrOutTypeText":"零售单出库"},{"dataID":28,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":90,"nowNum":300,"inOrOut":1,"inOrOutType":13,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":8,"orderID":3,"addTime":1587542953,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"领货归还单入库"},{"dataID":27,"goodsID":1,"goodsMainImage":"1.jpg","goodsName":"MacBook Pro","goodsNum":"SP0001","catName":"Apple","goodsAttr":"最新的","num":10,"nowNum":210,"inOrOut":1,"inOrOutType":13,"storeID":1,"storeType":1,"addUserID":1,"addUserName":"ewenXing","orderType":8,"orderID":3,"addTime":1587542891,"storeName":"总仓","inOrOutText":"入库","inOrOutTypeText":"领货归还单入库"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * dataID : 41
             * goodsID : 9
             * goodsMainImage :
             * goodsName : iPhone
             * goodsNum : iPhone11_001
             * catName :
             * goodsAttr : iPhone11
             * num : 1000
             * nowNum : 1000
             * inOrOut : 1
             * inOrOutType : 22
             * storeID : 1
             * storeType : 1
             * addUserID : 1
             * addUserName : ewenXing
             * orderType : 0
             * orderID : 0
             * addTime : 1588228260
             * storeName : 总仓
             * inOrOutText : 入库
             * inOrOutTypeText : Excel导入商品入库
             */

            private int dataID;
            private int goodsID;
            private String goodsMainImage;
            private String goodsName;
            private String goodsNum;
            private String catName;
            private String goodsAttr;
            private int num;
            private int nowNum;
            private int inOrOut;
            private int inOrOutType;
            private String storeID;
            private int storeType;
            private String addUserID;
            private String addUserName;
            private int orderType;
            private String orderID;
            private long addTime;
            private String storeName;
            private String inOrOutText;
            private String inOrOutTypeText;

            public int getDataID() {
                return dataID;
            }

            public void setDataID(int dataID) {
                this.dataID = dataID;
            }

            public int getGoodsID() {
                return goodsID;
            }

            public void setGoodsID(int goodsID) {
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

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getNowNum() {
                return nowNum;
            }

            public void setNowNum(int nowNum) {
                this.nowNum = nowNum;
            }

            public int getInOrOut() {
                return inOrOut;
            }

            public void setInOrOut(int inOrOut) {
                this.inOrOut = inOrOut;
            }

            public int getInOrOutType() {
                return inOrOutType;
            }

            public void setInOrOutType(int inOrOutType) {
                this.inOrOutType = inOrOutType;
            }

            public String getStoreID() {
                return storeID;
            }

            public void setStoreID(String storeID) {
                this.storeID = storeID;
            }

            public int getStoreType() {
                return storeType;
            }

            public void setStoreType(int storeType) {
                this.storeType = storeType;
            }

            public String getAddUserID() {
                return addUserID;
            }

            public void setAddUserID(String addUserID) {
                this.addUserID = addUserID;
            }

            public String getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(String addUserName) {
                this.addUserName = addUserName;
            }

            public int getOrderType() {
                return orderType;
            }

            public void setOrderType(int orderType) {
                this.orderType = orderType;
            }

            public String getOrderID() {
                return orderID;
            }

            public void setOrderID(String orderID) {
                this.orderID = orderID;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getInOrOutText() {
                return inOrOutText;
            }

            public void setInOrOutText(String inOrOutText) {
                this.inOrOutText = inOrOutText;
            }

            public String getInOrOutTypeText() {
                return inOrOutTypeText;
            }

            public void setInOrOutTypeText(String inOrOutTypeText) {
                this.inOrOutTypeText = inOrOutTypeText;
            }
        }
    }
}
