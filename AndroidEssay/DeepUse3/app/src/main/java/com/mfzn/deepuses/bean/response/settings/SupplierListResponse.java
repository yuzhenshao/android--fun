package com.mfzn.deepuses.bean.response.settings;

import java.io.Serializable;
import java.util.List;

public class SupplierListResponse {

    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"supplierID":1,"supplierName":"供应商1","supplierCode":"RWIJ_GYS_000001","chargePerson":"王先生","chargePersonPhone":"15311111111","contactAddress":"安通大厦","addTime":1583767046,"orderList":[{"orderID":3,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000003","realMoney":"10000.00","hasDoneMoney":"1000.00","needDoneMoney":"9000.00","storeID":1,"storeName":"总仓","isInStore":0,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":1,"payID":3},{"orderID":2,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000002","realMoney":"10000.00","hasDoneMoney":"0.00","needDoneMoney":"10000.00","storeID":1,"storeName":"总仓","isInStore":2,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":0,"payID":2},{"orderID":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000001","realMoney":"10000.00","hasDoneMoney":"10000.00","needDoneMoney":"0.00","storeID":1,"storeName":"总仓","isInStore":2,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":2,"payID":1}],"payOrGatheringLogList":[{"logID":7,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587050106},{"logID":6,"moneyAccountID":1,"accountName":"账户1","money":"8500.00","addTime":1587047857},{"logID":2,"moneyAccountID":1,"accountName":"账户1","money":"500.00","addTime":1587039953},{"logID":1,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587039789}]},{"supplierID":2,"supplierName":"供应商2","supplierCode":"RWIJ_GYS_000002","chargePerson":"小王","chargePersonPhone":"15311111111","contactAddress":"常州武进","addTime":1586948879,"orderList":[],"payOrGatheringLogList":[]}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<SupplierResponse> data;

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

    public List<SupplierResponse> getData() {
        return data;
    }

    public void setData(List<SupplierResponse> data) {
        this.data = data;
    }

    public static class SupplierResponse implements Serializable{
        /**
         * supplierID : 1
         * supplierName : 供应商1
         * supplierCode : RWIJ_GYS_000001
         * chargePerson : 王先生
         * chargePersonPhone : 15311111111
         * contactAddress : 安通大厦
         * addTime : 1583767046
         * orderList : [{"orderID":3,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000003","realMoney":"10000.00","hasDoneMoney":"1000.00","needDoneMoney":"9000.00","storeID":1,"storeName":"总仓","isInStore":0,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":1,"payID":3},{"orderID":2,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000002","realMoney":"10000.00","hasDoneMoney":"0.00","needDoneMoney":"10000.00","storeID":1,"storeName":"总仓","isInStore":2,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":0,"payID":2},{"orderID":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000001","realMoney":"10000.00","hasDoneMoney":"10000.00","needDoneMoney":"0.00","storeID":1,"storeName":"总仓","isInStore":2,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":2,"payID":1}]
         * payOrGatheringLogList : [{"logID":7,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587050106},{"logID":6,"moneyAccountID":1,"accountName":"账户1","money":"8500.00","addTime":1587047857},{"logID":2,"moneyAccountID":1,"accountName":"账户1","money":"500.00","addTime":1587039953},{"logID":1,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587039789}]
         */

        private String supplierID;
        private String supplierName;
        private String supplierCode;
        private String chargePerson;
        private String chargePersonPhone;
        private String contactAddress;
        private long addTime;
        private List<OrderList> orderList;
        private List<PayOrGatheringLog> payOrGatheringLogList;

        public String getSupplierID() {
            return supplierID;
        }

        public void setSupplierID(String supplierID) {
            this.supplierID = supplierID;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public String getChargePerson() {
            return chargePerson;
        }

        public void setChargePerson(String chargePerson) {
            this.chargePerson = chargePerson;
        }

        public String getChargePersonPhone() {
            return chargePersonPhone;
        }

        public void setChargePersonPhone(String chargePersonPhone) {
            this.chargePersonPhone = chargePersonPhone;
        }

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public List<OrderList> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderList> orderList) {
            this.orderList = orderList;
        }

        public List<PayOrGatheringLog> getPayOrGatheringLogList() {
            return payOrGatheringLogList;
        }

        public void setPayOrGatheringLogList(List<PayOrGatheringLog> payOrGatheringLogList) {
            this.payOrGatheringLogList = payOrGatheringLogList;
        }

        public static class OrderList implements Serializable{
            /**
             * orderID : 3
             * orderTime : 1586879276
             * orderNum : RWIJ_CG_00000003
             * realMoney : 10000.00
             * hasDoneMoney : 1000.00
             * needDoneMoney : 9000.00
             * storeID : 1
             * storeName : 总仓
             * isInStore : 0
             * orderMakerUserID : 1
             * orderMakerUserName : ewenXing
             * isPay : 1
             * payID : 3
             */

            private String orderID;
            private long orderTime;
            private String orderNum;
            private String realMoney;
            private String hasDoneMoney;
            private String needDoneMoney;
            private String storeID;
            private String storeName;
            private int isInStore;
            private String orderMakerUserID;
            private String orderMakerUserName;
            private int isPay;
            private int payID;

            public String getOrderID() {
                return orderID;
            }

            public void setOrderID(String orderID) {
                this.orderID = orderID;
            }

            public long getOrderTime() {
                return orderTime;
            }

            public void setOrderTime(long orderTime) {
                this.orderTime = orderTime;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getRealMoney() {
                return realMoney;
            }

            public void setRealMoney(String realMoney) {
                this.realMoney = realMoney;
            }

            public String getHasDoneMoney() {
                return hasDoneMoney;
            }

            public void setHasDoneMoney(String hasDoneMoney) {
                this.hasDoneMoney = hasDoneMoney;
            }

            public String getNeedDoneMoney() {
                return needDoneMoney;
            }

            public void setNeedDoneMoney(String needDoneMoney) {
                this.needDoneMoney = needDoneMoney;
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

            public int getIsInStore() {
                return isInStore;
            }

            public void setIsInStore(int isInStore) {
                this.isInStore = isInStore;
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

            public int getIsPay() {
                return isPay;
            }

            public void setIsPay(int isPay) {
                this.isPay = isPay;
            }

            public int getPayID() {
                return payID;
            }

            public void setPayID(int payID) {
                this.payID = payID;
            }
        }

        public static class PayOrGatheringLog implements Serializable{
            /**
             * logID : 7
             * moneyAccountID : 1
             * accountName : 账户1
             * money : 1000.00
             * addTime : 1587050106
             */

            private int logID;
            private int moneyAccountID;
            private String accountName;
            private String money;
            private int addTime;

            public int getLogID() {
                return logID;
            }

            public void setLogID(int logID) {
                this.logID = logID;
            }

            public int getMoneyAccountID() {
                return moneyAccountID;
            }

            public void setMoneyAccountID(int moneyAccountID) {
                this.moneyAccountID = moneyAccountID;
            }

            public String getAccountName() {
                return accountName;
            }

            public void setAccountName(String accountName) {
                this.accountName = accountName;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getAddTime() {
                return addTime;
            }

            public void setAddTime(int addTime) {
                this.addTime = addTime;
            }
        }
    }
}
