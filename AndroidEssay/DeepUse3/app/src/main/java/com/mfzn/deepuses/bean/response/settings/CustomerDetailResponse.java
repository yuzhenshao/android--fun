package com.mfzn.deepuses.bean.response.settings;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class CustomerDetailResponse {


    /**
     * baseInfo : {"payeeName":"周方方","chargePerson":"","chargePersonPhone":"","contactAddress":"","remark":"","sumPay":6,"sumNeedPay":500004,"sumGathering":0,"sumNeedGathering":533727.56}
     * orderList : [{"dataID":90,"dataType":2,"orderID":96,"orderType":4,"money":"10.00","hasDoneMoney":"6.00","needDoneMoney":"4.00","status":1,"orderName":"销售单","orderNum":"RWIJ_XS_00000035","orderTime":1594051674,"orderMakerUserID":1,"inOrOutStoreStatus":0,"storeID":1,"inOrOutStore":1,"storeName":"总仓","orderMakerUserName":"Ewen"},{"dataID":89,"dataType":1,"orderID":96,"orderType":4,"money":"90000.00","hasDoneMoney":"0.00","needDoneMoney":"90000.00","status":2,"orderNum":"RWIJ_XS_00000035","orderTime":1594051674,"orderMakerUserID":1,"inOrOutStoreStatus":2,"storeID":1,"inOrOutStore":2,"storeName":"总仓","orderMakerUserName":"Ewen"},{"dataID":32,"dataType":2,"orderID":45,"orderType":6,"money":"500000.00","hasDoneMoney":"0.00","needDoneMoney":"500000.00","status":0,"orderName":"销售退货单","orderNum":"RWIJ_XSTH_00000030","orderTime":1592227885,"orderMakerUserID":2,"inOrOutStoreStatus":1,"storeID":1,"inOrOutStore":1,"storeName":"总仓","orderMakerUserName":"augus"},{"dataID":23,"dataType":1,"orderID":29,"orderType":4,"money":"4995.00","hasDoneMoney":"0.00","needDoneMoney":"4995.00","status":0,"orderNum":"RWIJ_XS_00000025","orderTime":1590240127,"orderMakerUserID":2,"inOrOutStoreStatus":0,"storeID":1,"inOrOutStore":2,"storeName":"总仓","orderMakerUserName":"augus"},{"dataID":9,"dataType":1,"orderID":28,"orderType":4,"money":"4995.00","hasDoneMoney":"0.00","needDoneMoney":"4995.00","status":0,"orderNum":"RWIJ_XS_00000024","orderTime":1589648363,"orderMakerUserID":2,"inOrOutStoreStatus":0,"storeID":1,"inOrOutStore":2,"storeName":"总仓","orderMakerUserName":"augus"},{"dataID":5,"dataType":1,"orderID":23,"orderType":4,"money":"416337.56","hasDoneMoney":"0.00","needDoneMoney":"416337.56","status":0,"orderNum":"RWIJ_XS_00000019","orderTime":1589786599,"orderMakerUserID":2,"inOrOutStoreStatus":0,"storeID":1,"inOrOutStore":2,"storeName":"总仓","orderMakerUserName":"augus"},{"dataID":4,"dataType":1,"orderID":24,"orderType":4,"money":"17400.00","hasDoneMoney":"0.00","needDoneMoney":"17400.00","status":0,"orderNum":"RWIJ_XS_00000020","orderTime":1589786989,"orderMakerUserID":2,"inOrOutStoreStatus":0,"storeID":1,"inOrOutStore":2,"storeName":"总仓","orderMakerUserName":"augus"}]
     * payLogList : [{"logID":179,"moneyAccountID":8,"orderNum":"RWIJ_XS_00000035","accountName":"现金账户","money":"5.00","addTime":1594052486,"type":2},{"logID":192,"moneyAccountID":8,"orderNum":"RWIJ_XS_00000035","accountName":"现金账户","money":"1.00","addTime":1594184534,"type":2}]
     */

    private BaseInfoBean baseInfo;
    private List<OrderListBean> orderList;
    private List<PayLogListBean> payLogList;

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
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

    public static class BaseInfoBean {
        /**
         * payeeName : 周方方
         * chargePerson :
         * chargePersonPhone :
         * contactAddress :
         * remark :
         * sumPay : 6
         * sumNeedPay : 500004
         * sumGathering : 0
         * sumNeedGathering : 533727.56
         */

        private String payeeName;
        private String chargePerson;
        private String chargePersonPhone;
        private String contactAddress;
        private String remark;
        private int sumPay;
        private int sumNeedPay;
        private int sumGathering;
        private double sumNeedGathering;

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

        public int getSumGathering() {
            return sumGathering;
        }

        public void setSumGathering(int sumGathering) {
            this.sumGathering = sumGathering;
        }

        public double getSumNeedGathering() {
            return sumNeedGathering;
        }

        public void setSumNeedGathering(double sumNeedGathering) {
            this.sumNeedGathering = sumNeedGathering;
        }
    }

    public static class OrderListBean implements MultiItemEntity, Serializable {
        /**
         * dataID : 90
         * dataType : 2
         * orderID : 96
         * orderType : 4
         * money : 10.00
         * hasDoneMoney : 6.00
         * needDoneMoney : 4.00
         * status : 1
         * orderName : 销售单
         * orderNum : RWIJ_XS_00000035
         * orderTime : 1594051674
         * orderMakerUserID : 1
         * inOrOutStoreStatus : 0
         * storeID : 1
         * inOrOutStore : 1
         * storeName : 总仓
         * orderMakerUserName : Ewen
         */

        private String dataID;
        private int dataType;//收付款类型：1.应收；2.应付；
        private String orderID;
        private int orderType;
        private String money;
        private String hasDoneMoney;
        private String needDoneMoney;
        private int status;
        private String orderName;
        private String orderNum;
        private long orderTime;
        private String orderMakerUserID;
        private int inOrOutStoreStatus;
        private String storeID;
        private int inOrOutStore;//出入库类型：1.入库；2.出库；
        private String storeName;
        private String orderMakerUserName;

        public String getDataID() {
            return dataID;
        }

        public void setDataID(String dataID) {
            this.dataID = dataID;
        }

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
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

        public String getStoreID() {
            return storeID;
        }

        public void setStoreID(String storeID) {
            this.storeID = storeID;
        }

        public int getInOrOutStore() {
            return inOrOutStore;
        }

        public void setInOrOutStore(int inOrOutStore) {
            this.inOrOutStore = inOrOutStore;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getOrderMakerUserName() {
            return orderMakerUserName;
        }

        public void setOrderMakerUserName(String orderMakerUserName) {
            this.orderMakerUserName = orderMakerUserName;
        }

        @Override
        public int getItemType() {
            return dataType;
        }

        public String getInOrOutStoreName() {
            return inOrOutStore==1?"入库":"出库";
        }
    }

    public static class PayLogListBean implements Serializable{
        /**
         * logID : 179
         * moneyAccountID : 8
         * orderNum : RWIJ_XS_00000035
         * accountName : 现金账户
         * money : 5.00
         * addTime : 1594052486
         * type : 2
         */

        private String logID;
        private String moneyAccountID;
        private String orderNum;
        private String accountName;
        private String money;
        private long addTime;
        private int type;

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

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
