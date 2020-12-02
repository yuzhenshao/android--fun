package com.mfzn.deepuses.bean.response;

import java.io.Serializable;
import java.util.List;

public class SupplierDetailResponse implements Serializable{


    /**
     * supplierID : 1
     * supplierName : 供应商1
     * supplierAvatar :
     * supplierCode : RWIJ_GYS_000001
     * chargePerson : 王先生
     * chargePersonPhone : 15311111111
     * contactAddress : 安通大厦
     * addTime : 1583767046
     * remark :
     * sumPay : 11000
     * sumNeedPay : 19000
     * orderList : [{"orderID":3,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000003","realMoney":"10000.00","remark":"哈哈哈","hasDoneMoney":"1000.00","needDoneMoney":"9000.00","storeID":1,"storeName":"总仓","isInStore":0,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":1,"payID":3},{"orderID":2,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000002","realMoney":"10000.00","remark":"哈哈哈","hasDoneMoney":"0.00","needDoneMoney":"10000.00","storeID":1,"storeName":"总仓","isInStore":2,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":0,"payID":2},{"orderID":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000001","realMoney":"10000.00","remark":"哈哈哈1","hasDoneMoney":"10000.00","needDoneMoney":"0.00","storeID":1,"storeName":"总仓","isInStore":2,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","isPay":2,"payID":1}]
     * payOrGatheringLogList : [{"logID":7,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587050106,"orderNum":""},{"logID":6,"moneyAccountID":1,"accountName":"账户1","money":"8500.00","addTime":1587047857,"orderNum":""},{"logID":2,"moneyAccountID":1,"accountName":"账户1","money":"500.00","addTime":1587039953,"orderNum":""},{"logID":1,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587039789,"orderNum":""}]
     */

    private String supplierID;
    private String supplierName;
    private String supplierAvatar;
    private String supplierCode;
    private String chargePerson;
    private String chargePersonPhone;
    private String contactAddress;
    private long addTime;
    private String remark;
    private int sumPay;
    private int sumNeedPay;
    private List<OrderListBean> orderList;
    private List<PayOrGatheringLogListBean> payOrGatheringLogList;

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

    public String getSupplierAvatar() {
        return supplierAvatar;
    }

    public void setSupplierAvatar(String supplierAvatar) {
        this.supplierAvatar = supplierAvatar;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSumPay() {
        return sumPay;
    }

    public void setSumPay(int sumPay) {
        this.sumPay = sumPay;
    }

    public int getSumNeedPay() {
        return sumNeedPay;
    }

    public void setSumNeedPay(int sumNeedPay) {
        this.sumNeedPay = sumNeedPay;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public List<PayOrGatheringLogListBean> getPayOrGatheringLogList() {
        return payOrGatheringLogList;
    }

    public void setPayOrGatheringLogList(List<PayOrGatheringLogListBean> payOrGatheringLogList) {
        this.payOrGatheringLogList = payOrGatheringLogList;
    }

    public static class OrderListBean implements Serializable{
        /**
         * orderID : 3
         * orderTime : 1586879276
         * orderNum : RWIJ_CG_00000003
         * realMoney : 10000.00
         * remark : 哈哈哈
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
        private String remark;
        private String hasDoneMoney;
        private String needDoneMoney;
        private String storeID;
        private String storeName;
        private int isInStore;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private int isPay;
        private String payID;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getPayID() {
            return payID;
        }

        public void setPayID(String payID) {
            this.payID = payID;
        }
    }

    public static class PayOrGatheringLogListBean implements Serializable {
        /**
         * logID : 7
         * moneyAccountID : 1
         * accountName : 账户1
         * money : 1000.00
         * addTime : 1587050106
         * orderNum :
         */

        private String logID;
        private String moneyAccountID;
        private String accountName;
        private String money;
        private long addTime;
        private String orderNum;

        public String getLogID() {
            return logID;
        }

        public void setLogID(String logID) {
            this.logID = logID;
        }

        public String getMoneyAccountID() {
            return moneyAccountID;
        }

        public void setMoneyAccountID(String moneyAccountID) {
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

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }
    }
}
