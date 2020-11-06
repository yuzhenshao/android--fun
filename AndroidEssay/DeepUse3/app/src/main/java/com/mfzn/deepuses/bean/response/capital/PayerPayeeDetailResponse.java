package com.mfzn.deepuses.bean.response.capital;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PayerPayeeDetailResponse implements Serializable{


    /**
     * baseInfo : {"payerName":"供应商1","chargePerson":"王先生","chargePersonPhone":"15311111111","contactAddress":"安通大厦","remark":"","sumGathering":10000,"sumNeedGathering":0}
     * orderList : [{"dataID":4,"orderID":4,"orderType":2,"money":"10000.00","hasDoneMoney":"10000.00","needDoneMoney":"0.00","status":2,"orderNum":"RWIJ_CGTH_00000004","orderTime":1586879276,"orderMakerUserID":1,"inOrOutStoreStatus":0,"inOrOutStore":2,"orderMakerUserName":"ewenXing"}]
     * payLogList : [{"logID":8,"moneyAccountID":1,"accountName":"账户1","money":"1000.00","addTime":1587135270,"orderNum":"RWIJ_CGTH_00000004"},{"logID":9,"moneyAccountID":1,"accountName":"账户1","money":"9000.00","addTime":1587135350,"orderNum":"RWIJ_CGTH_00000004"}]
     */

    private BaseInfoResponse baseInfo;
    private List<OrderListResponse> orderList;
    private List<PayLogResponse> payLogList;

    public BaseInfoResponse getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoResponse baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<OrderListResponse> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListResponse> orderList) {
        this.orderList = orderList;
    }

    public List<PayLogResponse> getPayLogList() {
        return payLogList;
    }

    public void setPayLogList(List<PayLogResponse> payLogList) {
        this.payLogList = payLogList;
    }

    public static class BaseInfoResponse implements Serializable{
        /**
         * payerName : 供应商1
         * chargePerson : 王先生
         * chargePersonPhone : 15311111111
         * contactAddress : 安通大厦
         * remark :
         * sumGathering : 10000
         * sumNeedGathering : 0
         */
        @SerializedName(value = "payeeName", alternate = {"payerName"})
        private String payeeName;
        private String chargePerson;
        private String chargePersonPhone;
        private String contactAddress;
        private String remark;
        @SerializedName(value = "sumGathering", alternate = {"sumPay"})
        private int sumGathering;
        @SerializedName(value = "sumNeedGathering", alternate = {"sumNeedPay"})
        private int sumNeedGathering;

        public String getPayerName() {
            return payeeName;
        }

        public void setPayerName(String payerName) {
            this.payeeName = payerName;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSumGathering() {
            return sumGathering;
        }

        public void setSumGathering(int sumGathering) {
            this.sumGathering = sumGathering;
        }

        public int getSumNeedGathering() {
            return sumNeedGathering;
        }

        public void setSumNeedGathering(int sumNeedGathering) {
            this.sumNeedGathering = sumNeedGathering;
        }
    }

    public static class OrderListResponse implements Serializable {
        /**
         * dataID : 4
         * orderID : 4
         * orderType : 2
         * money : 10000.00
         * hasDoneMoney : 10000.00
         * needDoneMoney : 0.00
         * status : 2
         * orderNum : RWIJ_CGTH_00000004
         * orderTime : 1586879276
         * orderMakerUserID : 1
         * inOrOutStoreStatus : 0
         * inOrOutStore : 2
         * orderMakerUserName : ewenXing
         */

        private String dataID;
        private String orderID;
        private int orderType;
        private String money;
        private String hasDoneMoney;
        private String needDoneMoney;
        private int status;
        private String orderNum;
        private long orderTime;
        private String orderMakerUserID;
        private int inOrOutStoreStatus;
        private int inOrOutStore;
        private String orderMakerUserName;

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

        public String getOrderMakerUserID() {
            return orderMakerUserID;
        }

        public void setOrderMakerUserID(String orderMakerUserID) {
            this.orderMakerUserID = orderMakerUserID;
        }

        public int getInOrOutStoreStatus() {
            return inOrOutStoreStatus;
        }

        public void setInOrOutStoreStatus(int inOrOutStoreStatus) {
            this.inOrOutStoreStatus = inOrOutStoreStatus;
        }

        public int getInOrOutStore() {
            return inOrOutStore;
        }

        public void setInOrOutStore(int inOrOutStore) {
            this.inOrOutStore = inOrOutStore;
        }

        public String getOrderMakerUserName() {
            return orderMakerUserName;
        }

        public void setOrderMakerUserName(String orderMakerUserName) {
            this.orderMakerUserName = orderMakerUserName;
        }
    }

    public static class PayLogResponse implements Serializable{
        /**
         * logID : 8
         * moneyAccountID : 1
         * accountName : 账户1
         * money : 1000.00
         * addTime : 1587135270
         * orderNum : RWIJ_CGTH_00000004
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
