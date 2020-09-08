package com.mfzn.deepuses.bean.response.user;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WaitingCheckListResponse {


    /**
     * total : 4
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"applyID":18,"companyID":2,"shopID":1,"checkStatus":0,"checkUserID":1,"checkTime":0,"checkNote":"","orderType":1,"addTime":1588081791,"orderID":7,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000006","supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","goodsAllCount":100},{"applyID":7,"companyID":2,"shopID":1,"checkStatus":1,"checkUserID":1,"checkTime":1587050008,"checkNote":"通过","orderType":1,"addTime":1587049843,"orderID":3,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000003","supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","goodsAllCount":100},{"applyID":6,"companyID":2,"shopID":1,"checkStatus":1,"checkUserID":1,"checkTime":1586921550,"checkNote":"通过","orderType":1,"addTime":1586879437,"orderID":2,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000002","supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","goodsAllCount":100},{"applyID":5,"companyID":2,"shopID":1,"checkStatus":1,"checkUserID":1,"checkTime":1586921311,"checkNote":"通过","orderType":1,"addTime":1586879361,"orderID":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000001","supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","goodsAllCount":100}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<WaitingCheckOrderPurchaseResponse> data;

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

    public List<WaitingCheckOrderPurchaseResponse> getData() {
        return data;
    }

    public void setData(List<WaitingCheckOrderPurchaseResponse> data) {
        this.data = data;
    }

    public static class WaitingCheckOrderPurchaseResponse implements Serializable{
        /**
         * applyID : 18
         * companyID : 2
         * shopID : 1
         * checkStatus : 0
         * checkUserID : 1
         * checkTime : 0
         * checkNote :
         * orderType : 1
         * addTime : 1588081791
         * orderID : 7
         * orderTime : 1586879276
         * orderNum : RWIJ_CG_00000006
         * supplierID : 1
         * supplierName : 供应商1
         * realMoney : 10000.00
         * orderMakerUserID : 1
         * orderMakerUserName : ewenXing
         * goodsAllCount : 100
         */

        private String applyID;
        private String companyID;
        private String shopID;
        private int checkStatus;
        private String checkUserID;
        private long checkTime;
        private String checkNote;
        private int orderType;
        private long addTime;
        private String orderID;
        private long orderTime;
        private String orderNum;

        @SerializedName(value = "supplierID", alternate = {"customerID"})
        private String supplierID;
        @SerializedName(value = "supplierName", alternate = {"customerName"})
        private String supplierName;
        private String realMoney;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private int goodsAllCount;

        //销售
        @SerializedName(value = "salesPersonUserID", alternate = {"takerUserID"})
        private String salesPersonUserID;
        @SerializedName(value = "salesPersonUserName", alternate = {"takerUserName"})
        private String salesPersonUserName;

        //个人领用单
        @SerializedName(value = "storeID", alternate = {"fromStoreID"})
        private String storeID;
        @SerializedName(value = "storeName", alternate = {"fromStoreName"})
        private String storeName;

        //调拨单
        private String toStoreID;
        private String toStoreName;

        private List<GoodsInfoResponse> goodsInfo;

        public String getApplyID() {
            return applyID;
        }

        public void setApplyID(String applyID) {
            this.applyID = applyID;
        }

        public String getCompanyID() {
            return companyID;
        }

        public void setCompanyID(String companyID) {
            this.companyID = companyID;
        }

        public String getShopID() {
            return shopID;
        }

        public void setShopID(String shopID) {
            this.shopID = shopID;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCheckUserID() {
            return checkUserID;
        }

        public void setCheckUserID(String checkUserID) {
            this.checkUserID = checkUserID;
        }

        public long getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(long checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckNote() {
            return checkNote;
        }

        public void setCheckNote(String checkNote) {
            this.checkNote = checkNote;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

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

        public String getRealMoney() {
            return realMoney;
        }

        public void setRealMoney(String realMoney) {
            this.realMoney = realMoney;
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

        public int getGoodsAllCount() {
            return goodsAllCount;
        }

        public void setGoodsAllCount(int goodsAllCount) {
            this.goodsAllCount = goodsAllCount;
        }

        public String getSalesPersonUserID() {
            return salesPersonUserID;
        }

        public void setSalesPersonUserID(String salesPersonUserID) {
            this.salesPersonUserID = salesPersonUserID;
        }

        public String getSalesPersonUserName() {
            return salesPersonUserName;
        }

        public void setSalesPersonUserName(String salesPersonUserName) {
            this.salesPersonUserName = salesPersonUserName;
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

        public String getToStoreID() {
            return toStoreID;
        }

        public void setToStoreID(String toStoreID) {
            this.toStoreID = toStoreID;
        }

        public String getToStoreName() {
            return toStoreName;
        }

        public void setToStoreName(String toStoreName) {
            this.toStoreName = toStoreName;
        }

        public List<GoodsInfoResponse> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public List<String> getGoodsMainImageList() {
            List<String> images = new ArrayList<>();
            if (!ListUtil.isEmpty(goodsInfo)) {
                for (GoodsInfoResponse goodsResponse : goodsInfo) {
                    if (!TextUtils.isEmpty(goodsResponse.getGoodsMainImage())) {
                        images.add(goodsResponse.getGoodsMainImage());
                    }
                }
            }
            return images;
        }
    }
}
