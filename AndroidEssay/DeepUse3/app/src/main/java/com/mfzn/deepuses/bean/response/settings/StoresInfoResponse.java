package com.mfzn.deepuses.bean.response.settings;

import java.io.Serializable;
import java.util.List;

/**
 * @author yz @date 2020-05-04
 */
public class StoresInfoResponse implements Serializable {
    /**
     * storeID : 1
     * warningMin : 10
     * warningMax : 1000
     * storeName : 总仓
     * stockNum : 300
     * stockLog : [{"logID":34,"num":10,"inOrOut":2,"inOrOutType":21,"storeID":1,"orderType":13,"orderID":7,"addTime":1587796795},{"logID":33,"num":10,"inOrOut":1,"inOrOutType":19,"storeID":1,"orderType":13,"orderID":7,"addTime":1587643307},{"logID":32,"num":10,"inOrOut":1,"inOrOutType":20,"storeID":1,"orderType":5,"orderID":6,"addTime":1587635897},{"logID":31,"num":10,"inOrOut":2,"inOrOutType":18,"storeID":1,"orderType":5,"orderID":6,"addTime":1587632109},{"logID":30,"num":10,"inOrOut":2,"inOrOutType":18,"storeID":1,"orderType":5,"orderID":5,"addTime":1587632031},{"logID":29,"num":10,"inOrOut":2,"inOrOutType":18,"storeID":1,"orderType":5,"orderID":4,"addTime":1587623380},{"logID":28,"num":90,"inOrOut":1,"inOrOutType":13,"storeID":1,"orderType":8,"orderID":3,"addTime":1587542953},{"logID":27,"num":10,"inOrOut":1,"inOrOutType":13,"storeID":1,"orderType":8,"orderID":3,"addTime":1587542891},{"logID":26,"num":10,"inOrOut":2,"inOrOutType":17,"storeID":1,"orderType":8,"orderID":3,"addTime":1587542891},{"logID":25,"num":90,"inOrOut":2,"inOrOutType":11,"storeID":1,"orderType":7,"orderID":2,"addTime":1587485541},{"logID":24,"num":90,"inOrOut":1,"inOrOutType":16,"storeID":1,"orderType":7,"orderID":2,"addTime":1587485541},{"logID":23,"num":10,"inOrOut":2,"inOrOutType":11,"storeID":1,"orderType":7,"orderID":2,"addTime":1587485488},{"logID":22,"num":10,"inOrOut":1,"inOrOutType":16,"storeID":1,"orderType":7,"orderID":2,"addTime":1587485488},{"logID":21,"num":100,"inOrOut":1,"inOrOutType":1,"storeID":1,"orderType":1,"orderID":1,"addTime":1586943147},{"logID":20,"num":80,"inOrOut":1,"inOrOutType":1,"storeID":1,"orderType":1,"orderID":2,"addTime":1586942890},{"logID":19,"num":10,"inOrOut":1,"inOrOutType":1,"storeID":1,"orderType":1,"orderID":2,"addTime":1586942527},{"logID":18,"num":10,"inOrOut":1,"inOrOutType":1,"storeID":1,"orderType":1,"orderID":2,"addTime":1586942048},{"logID":17,"num":1,"inOrOut":1,"inOrOutType":14,"storeID":1,"orderType":16,"orderID":1,"addTime":1586597986},{"logID":16,"num":1,"inOrOut":2,"inOrOutType":15,"storeID":1,"orderType":12,"orderID":1,"addTime":1586449322},{"logID":13,"num":8,"inOrOut":2,"inOrOutType":7,"storeID":1,"orderType":11,"orderID":1,"addTime":1586160723}]
     */

    private int storeID;
    private int warningMin;
    private int warningMax;
    private String storeName;
    private int stockNum;
    private List<StockLog> stockLog;

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
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

    public List<StockLog> getStockLog() {
        return stockLog;
    }

    public void setStockLog(List<StockLog> stockLog) {
        this.stockLog = stockLog;
    }

    public static class StockLog {
        /**
         * logID : 34
         * num : 10
         * inOrOut : 2
         * inOrOutType : 21
         * storeID : 1
         * orderType : 13
         * orderID : 7
         * addTime : 1587796795
         */

        private int logID;
        private int num;
        private int inOrOut;
        private int inOrOutType;
        private int storeID;
        private int orderType;
        private int orderID;
        private long addTime;

        public int getLogID() {
            return logID;
        }

        public void setLogID(int logID) {
            this.logID = logID;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public int getStoreID() {
            return storeID;
        }

        public void setStoreID(int storeID) {
            this.storeID = storeID;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
