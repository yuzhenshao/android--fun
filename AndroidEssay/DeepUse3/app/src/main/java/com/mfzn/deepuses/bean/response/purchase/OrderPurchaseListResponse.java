package com.mfzn.deepuses.bean.response.purchase;

import android.text.TextUtils;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderPurchaseListResponse {

    /**
     * total : 3
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderID":3,"companyID":2,"shopID":1,"orderType":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000003","isPay":1,"isInStore":0,"supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","isCanceled":0,"remark":"哈哈哈","isCheck":1,"checkTime":1587050008,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1587049843,"goodsAllCount":100,"isCheckText":"已通过","isCanceledText":"未作废","isPayText":"付款中","isInStoreText":"待入库"},{"orderID":2,"companyID":2,"shopID":1,"orderType":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000002","isPay":0,"isInStore":2,"supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","isCanceled":0,"remark":"哈哈哈","isCheck":1,"checkTime":1586921550,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1586879437,"goodsAllCount":100,"isCheckText":"已通过","isCanceledText":"未作废","isPayText":"待付款","isInStoreText":"已入库"},{"orderID":1,"companyID":2,"shopID":1,"orderType":1,"orderTime":1586879276,"orderNum":"RWIJ_CG_00000001","isPay":2,"isInStore":2,"supplierID":1,"supplierName":"供应商1","realMoney":"10000.00","orderMakerUserID":1,"orderMakerUserName":"ewenXing","isCanceled":0,"remark":"哈哈哈1","isCheck":1,"checkTime":1586921311,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1586879361,"goodsAllCount":100,"isCheckText":"已通过","isCanceledText":"未作废","isPayText":"已付款","isInStoreText":"已入库"}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<OrderPurchaseResponse> data;

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

    public List<OrderPurchaseResponse> getData() {
        return data;
    }

    public void setData(List<OrderPurchaseResponse> data) {
        this.data = data;
    }

    public static class OrderPurchaseResponse implements Serializable {
        /**
         * orderID : 3
         * companyID : 2
         * shopID : 1
         * orderType : 1
         * orderTime : 1586879276
         * orderNum : RWIJ_CG_00000003
         * isPay : 1
         * isInStore : 0
         * supplierID : 1
         * supplierName : 供应商1
         * realMoney : 10000.00
         * orderMakerUserID : 1
         * orderMakerUserName : ewenXing
         * isCanceled : 0
         * remark : 哈哈哈
         * isCheck : 1
         * checkTime : 1587050008
         * checkNote : 通过
         * checkUserID : 1
         * checkUserName : ewenXing
         * addTime : 1587049843
         * goodsAllCount : 100
         * isCheckText : 已通过
         * isCanceledText : 未作废
         * isPayText : 付款中
         * isInStoreText : 待入库
         */

        private String orderID;
        private String companyID;
        private String shopID;
        private String orderType;
        private long orderTime;
        private String orderNum;
        private int isPay;
        private int isInStore;
        private String supplierID;
        private String supplierName;
        private String realMoney;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private int isCanceled;
        private String remark;
        private int isCheck;
        private long checkTime;
        private String checkNote;
        private String checkUserID;
        private String checkUserName;
        private long addTime;
        private int goodsAllCount;
        private String isCheckText;
        private String isCanceledText;
        private String isPayText;
        private String isInStoreText;
        private List<GoodsInfoResponse> goodsInfo;

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
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

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
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

        public int getIsPay() {
            return isPay;
        }

        public void setIsPay(int isPay) {
            this.isPay = isPay;
        }

        public int getIsInStore() {
            return isInStore;
        }

        public void setIsInStore(int isInStore) {
            this.isInStore = isInStore;
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

        public int getIsCanceled() {
            return isCanceled;
        }

        public void setIsCanceled(int isCanceled) {
            this.isCanceled = isCanceled;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(int isCheck) {
            this.isCheck = isCheck;
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

        public String getCheckUserID() {
            return checkUserID;
        }

        public void setCheckUserID(String checkUserID) {
            this.checkUserID = checkUserID;
        }

        public String getCheckUserName() {
            return checkUserName;
        }

        public void setCheckUserName(String checkUserName) {
            this.checkUserName = checkUserName;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getGoodsAllCount() {
            return goodsAllCount;
        }

        public void setGoodsAllCount(int goodsAllCount) {
            this.goodsAllCount = goodsAllCount;
        }

        public String getIsCheckText() {
            return isCheckText;
        }

        public void setIsCheckText(String isCheckText) {
            this.isCheckText = isCheckText;
        }

        public String getIsCanceledText() {
            return isCanceledText;
        }

        public void setIsCanceledText(String isCanceledText) {
            this.isCanceledText = isCanceledText;
        }

        public String getIsPayText() {
            return isPayText;
        }

        public void setIsPayText(String isPayText) {
            this.isPayText = isPayText;
        }

        public String getIsInStoreText() {
            return isInStoreText;
        }

        public void setIsInStoreText(String isInStoreText) {
            this.isInStoreText = isInStoreText;
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
