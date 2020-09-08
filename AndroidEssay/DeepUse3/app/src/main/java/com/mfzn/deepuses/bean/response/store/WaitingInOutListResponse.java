package com.mfzn.deepuses.bean.response.store;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.util.ArrayList;
import java.util.List;

public class WaitingInOutListResponse {


    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"dataID":1,"orderID":2,"orderType":11,"orderNum":"RWIJ_DB_00000001","storeID":1,"relatedStoreID":2,"storeName":"总仓","isGathering":2,"status":0,"addTime":1585833207,"orderTypeName":"调拨单","receiverType":3,"receiverName":"分仓1","orderMakerUserID":1,"orderMakerUserName":"ewenXing","orderTime":1585830602}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<WaitingInOutResponse> data;

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

    public List<WaitingInOutResponse> getData() {
        return data;
    }

    public void setData(List<WaitingInOutResponse> data) {
        this.data = data;
    }

    public static class WaitingInOutResponse {
        /**
         * dataID : 1
         * orderID : 2
         * orderType : 11
         * orderNum : RWIJ_DB_00000001
         * storeID : 1
         * relatedStoreID : 2
         * storeName : 总仓
         * isGathering : 2
         * status : 0
         * addTime : 1585833207
         * orderTypeName : 调拨单
         * receiverType : 3
         * receiverName : 分仓1
         * orderMakerUserID : 1
         * orderMakerUserName : ewenXing
         * orderTime : 1585830602
         */

        private String dataID;
        private String orderID;
        private int orderType;
        private String orderNum;
        private String storeID;
        private String relatedStoreID;
        private String storeName;
        private int isGathering;
        private int status;
        private long addTime;
        private String orderTypeName;
        @SerializedName(value = "receiverType", alternate = {"senderType"})
        private int receiverType;
        @SerializedName(value = "receiverName", alternate = {"senderName"})
        private String receiverName;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private long orderTime;
        private List<GoodsInfoResponse> goodsInfo;

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

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getStoreID() {
            return storeID;
        }

        public void setStoreID(String storeID) {
            this.storeID = storeID;
        }

        public String getRelatedStoreID() {
            return relatedStoreID;
        }

        public void setRelatedStoreID(String relatedStoreID) {
            this.relatedStoreID = relatedStoreID;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getIsGathering() {
            return isGathering;
        }

        public void setIsGathering(int isGathering) {
            this.isGathering = isGathering;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getOrderTypeName() {
            return orderTypeName;
        }

        public void setOrderTypeName(String orderTypeName) {
            this.orderTypeName = orderTypeName;
        }

        public int getReceiverType() {
            return receiverType;
        }

        public void setReceiverType(int receiverType) {
            this.receiverType = receiverType;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
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

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public List<GoodsInfoResponse> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public List<String> getGoodsMainImageList(){
            List<String> images=new ArrayList<>();
            if(!ListUtil.isEmpty(goodsInfo)){
                for(GoodsInfoResponse goodsResponse:goodsInfo){
                    if(!TextUtils.isEmpty(goodsResponse.getGoodsMainImage())){
                        images.add(goodsResponse.getGoodsMainImage());
                    }
                }
            }
            return images;
        }
    }
}
