package com.mfzn.deepuses.bean.response.store;

import android.text.TextUtils;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderOtherInOutListResponse {
    /**
     * total : 2
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderID":2,"orderType":9,"companyID":2,"shopID":1,"supplierID":0,"customerID":1,"orderTime":1585377154,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","orderNum":"RWIJ_QTCK_00000002","totalMoney":"182.00","discountAmount":"2.00","realMoney":"180.00","remark":"这个是备注","isCheck":1,"checkTime":1586141237,"checkUserID":1,"status":1,"checkUserName":"ewenXing","checkNote":"通过","addTime":1586141198,"receiverInfo":{"receiverID":1,"receiverName":"ewenXing","receiverType":1},"statusText":"已通过待完成"},{"orderID":1,"orderType":10,"companyID":2,"shopID":1,"supplierID":1,"customerID":0,"orderTime":1585326782,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","orderNum":"RWIJ_QTRK_00000001","totalMoney":"210.00","discountAmount":"10.00","realMoney":"200.00","remark":"备注一下","isCheck":1,"checkTime":1586141093,"checkUserID":1,"status":3,"checkUserName":"ewenXing","checkNote":"","addTime":1586141093,"receiverInfo":{"receiverID":1,"receiverName":"供应商1","receiverType":2},"statusText":"已完成"}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<OrderOtherInOutResponse> data;

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

    public List<OrderOtherInOutResponse> getData() {
        return data;
    }

    public void setData(List<OrderOtherInOutResponse> data) {
        this.data = data;
    }

    public static class OrderOtherInOutResponse {
        /**
         * orderID : 2
         * orderType : 9
         * companyID : 2
         * shopID : 1
         * supplierID : 0
         * customerID : 1
         * orderTime : 1585377154
         * orderMakerUserID : 1
         * orderMakerUserName : ewenXing
         * orderNum : RWIJ_QTCK_00000002
         * totalMoney : 182.00
         * discountAmount : 2.00
         * realMoney : 180.00
         * remark : 这个是备注
         * isCheck : 1
         * checkTime : 1586141237
         * checkUserID : 1
         * status : 1
         * checkUserName : ewenXing
         * checkNote : 通过
         * addTime : 1586141198
         * receiverInfo : {"receiverID":1,"receiverName":"ewenXing","receiverType":1}
         * statusText : 已通过待完成
         */

        private String orderID;
        private int orderType;
        private String companyID;
        private String shopID;
        private String supplierID;
        private String customerID;
        private long orderTime;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private String orderNum;
        private String totalMoney;
        private String discountAmount;
        private String realMoney;
        private String remark;
        private int isCheck;
        private long checkTime;
        private long checkUserID;
        private int status;
        private String checkUserName;
        private String checkNote;
        private long addTime;
        private ReceiverInfoBean receiverInfo;
        private String statusText;
        private List<GoodsInfoResponse> goodsInfo;

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

        public String getSupplierID() {
            return supplierID;
        }

        public void setSupplierID(String supplierID) {
            this.supplierID = supplierID;
        }

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
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

        public String getOrderMakerUserName() {
            return orderMakerUserName;
        }

        public void setOrderMakerUserName(String orderMakerUserName) {
            this.orderMakerUserName = orderMakerUserName;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
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

        public long getCheckUserID() {
            return checkUserID;
        }

        public void setCheckUserID(long checkUserID) {
            this.checkUserID = checkUserID;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCheckUserName() {
            return checkUserName;
        }

        public void setCheckUserName(String checkUserName) {
            this.checkUserName = checkUserName;
        }

        public String getCheckNote() {
            return checkNote;
        }

        public void setCheckNote(String checkNote) {
            this.checkNote = checkNote;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public ReceiverInfoBean getReceiverInfo() {
            return receiverInfo;
        }

        public void setReceiverInfo(ReceiverInfoBean receiverInfo) {
            this.receiverInfo = receiverInfo;
        }

        public String getStatusText() {
            return statusText;
        }

        public void setStatusText(String statusText) {
            this.statusText = statusText;
        }

        public List<GoodsInfoResponse> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public static class ReceiverInfoBean {
            /**
             * receiverID : 1
             * receiverName : ewenXing
             * receiverType : 1
             */

            private String receiverID;
            private String receiverName;
            private int receiverType;

            public String getReceiverID() {
                return receiverID;
            }

            public void setReceiverID(String receiverID) {
                this.receiverID = receiverID;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public int getReceiverType() {
                return receiverType;
            }

            public void setReceiverType(int receiverType) {
                this.receiverType = receiverType;
            }
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
