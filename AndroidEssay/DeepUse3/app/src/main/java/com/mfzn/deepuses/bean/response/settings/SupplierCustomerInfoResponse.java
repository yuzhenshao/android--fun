package com.mfzn.deepuses.bean.response.settings;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class SupplierCustomerInfoResponse {

    /**
     * baseInfo : {"payeeName":"供应商1","chargePerson":"王先生","chargePersonPhone":"15311111111","contactAddress":"安通大厦","sumPay":10000,"sumNeedPay":10000}
     * orderList : [{"dataID":1,"orderID":1,"orderType":1,"money":"10000.00","hasDoneMoney":"10000.00","needDoneMoney":"0.00","status":2,"orderName":"采购单","orderNum":"RWIJ_CG_00000001","orderTime":1586879276},{"dataID":2,"orderID":2,"orderType":1,"money":"10000.00","hasDoneMoney":"0.00","needDoneMoney":"10000.00","status":0,"orderName":"采购单","orderNum":"RWIJ_CG_00000002","orderTime":1586879276}]
     * payLogList : [{"logID":1,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587039789,"orderNum":"RWIJ_CG_00000002"},{"logID":2,"moneyAccountID":1,"accountName":"账户1","money":"500.00","addTime":1587039953,"orderNum":"RWIJ_CG_00000002"},{"logID":6,"moneyAccountID":1,"accountName":"账户1","money":"8500.00","addTime":1587047857,"orderNum":"RWIJ_CG_00000002"}]
     */

    private SupplierBaseInfo baseInfo;
    private List<OrderListBean> orderList;
    private List<PayLogListBean> payLogList;

    public SupplierBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(SupplierBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public List<PayLogListBean> getPayLogList() {
        return payLogList;
    }

    public void setPayLogList(List<PayLogListBean> payLogList) {
        this.payLogList = payLogList;
    }

    public static class SupplierBaseInfo {
        /**
         * payeeName : 供应商1
         * chargePerson : 王先生
         * chargePersonPhone : 15311111111
         * contactAddress : 安通大厦
         * sumPay : 10000
         * sumNeedPay : 10000
         */

        private String payeeName;
        private String chargePerson;
        private String chargePersonPhone;
        private String contactAddress;
        private int sumPay;
        private int sumNeedPay;

        public String getPayeeName() {
            return payeeName;
        }

        public void setPayeeName(String payeeName) {
            this.payeeName = payeeName;
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
    }

    public static class OrderListBean implements Serializable, MultiItemEntity {
        /**
         * dataID : 1
         * orderID : 1
         * orderType : 1
         * money : 10000.00
         * hasDoneMoney : 10000.00
         * needDoneMoney : 0.00
         * status : 2
         * orderName : 采购单
         * orderNum : RWIJ_CG_00000001
         * orderTime : 1586879276
         */

        private String dataID;
        private String orderID;
        private int orderType;
        private String money;
        private String hasDoneMoney;
        private String needDoneMoney;
        private int status;
        private String orderName;
        private String orderNum;
        private long orderTime;

        public String getDataID() {
            return dataID;
        }

        public void setDataID(String dataID) {
            this.dataID = dataID;
        }

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }

    public static class PayLogListBean implements Serializable{
        /**
         * logID : 1
         * moneyAccountID : 1
         * accountName : 账户1
         * money : 1000.00
         * addTime : 1587039789
         * orderNum : RWIJ_CG_00000002
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
