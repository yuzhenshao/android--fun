package com.mfzn.deepuses.bean.response.sale;

import android.text.TextUtils;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderSalesListResponse {

    /**
     * total : 9
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"orderID":15,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000011","isGathering":0,"isOutStore":0,"isCanceled":0,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":0,"remark":"备注","isCheck":0,"checkTime":0,"checkNote":"","checkUserID":1,"checkUserName":"ewenXing","addTime":1591323751,"goodsInfo":[{"goodsID":1,"goodsCount":2,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"8888.00","uniformSalePrice":"500.00","salePrice":"500.00","salePriceWithTax":"500.00","taxRate":"0.01","money":"1000.00","goodsSumStockNum":390}],"goodsAllCount":2,"isCheckText":"待审核","isGatheringText":"待收款","isOutStoreText":"待出库","status":"未完成"},{"orderID":10,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000010","isGathering":0,"isOutStore":0,"isCanceled":0,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":0,"remark":"备注","isCheck":1,"checkTime":1590477884,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1590477854,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"1000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390}],"goodsAllCount":10,"isCheckText":"已通过","isGatheringText":"待收款","isOutStoreText":"待出库","status":"未完成"},{"orderID":9,"companyID":2,"shopID":1,"orderType":4,"orderTime":1590371056,"outNum":"wb001","orderNum":"RWIJ_XS_00000009","isGathering":0,"isOutStore":0,"isCanceled":0,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":0,"remark":"备注","isCheck":1,"checkTime":1590370991,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1590370961,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"1000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390}],"goodsAllCount":10,"isCheckText":"已通过","isGatheringText":"待收款","isOutStoreText":"待出库","status":"未完成"},{"orderID":8,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000008","isGathering":0,"isOutStore":0,"isCanceled":0,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":0,"remark":"备注","isCheck":1,"checkTime":1589812007,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1589811961,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"1000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390}],"goodsAllCount":10,"isCheckText":"已通过","isGatheringText":"待收款","isOutStoreText":"待出库","status":"未完成"},{"orderID":7,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000007","isGathering":0,"isOutStore":0,"isCanceled":0,"salesPersonUserID":2,"salesPersonUserName":"augus","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":0,"remark":"备注","isCheck":1,"checkTime":1589812002,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1589811921,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"1000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390}],"goodsAllCount":10,"isCheckText":"已通过","isGatheringText":"待收款","isOutStoreText":"待出库","status":"未完成"},{"orderID":6,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000006","isGathering":0,"isOutStore":0,"isCanceled":0,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":0,"remark":"备注","isCheck":1,"checkTime":1589812029,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1589164476,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"900.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390}],"goodsAllCount":10,"isCheckText":"已通过","isGatheringText":"待收款","isOutStoreText":"待出库","status":"未完成"},{"orderID":5,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000005","isGathering":2,"isOutStore":2,"isCanceled":1,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":3,"remark":"备注","isCheck":1,"checkTime":1588138018,"checkNote":"haode","checkUserID":1,"checkUserName":"ewenXing","addTime":1588138009,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"9000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390},{"goodsID":2,"goodsCount":100,"goodsName":"MacBook","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"9000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"100100.00","goodsSumStockNum":100}],"goodsAllCount":110,"isCheckText":"已通过","isGatheringText":"已收款","isOutStoreText":"已出库","status":"作废中"},{"orderID":4,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000004","isGathering":2,"isOutStore":2,"isCanceled":1,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":1,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":3,"remark":"备注","isCheck":1,"checkTime":1588136429,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1588136391,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"9000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390},{"goodsID":2,"goodsCount":100,"goodsName":"MacBook","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"9000.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"100100.00","goodsSumStockNum":100}],"goodsAllCount":110,"isCheckText":"已通过","isGatheringText":"已收款","isOutStoreText":"已出库","status":"作废中"},{"orderID":1,"companyID":2,"shopID":1,"orderType":4,"orderTime":1587192681,"outNum":"wb001","orderNum":"RWIJ_XS_00000001","isGathering":0,"isOutStore":0,"isCanceled":1,"salesPersonUserID":1,"salesPersonUserName":"ewenXing","customerID":6,"customerName":"李四","realMoney":"10000.00","orderMakerDiscount":100,"orderMakerUserID":1,"orderMakerUserName":"ewenXing","storeID":1,"storeName":"总仓","cancelType":3,"remark":"备注1","isCheck":1,"checkTime":1587195188,"checkNote":"通过","checkUserID":1,"checkUserName":"ewenXing","addTime":1587192992,"goodsInfo":[{"goodsID":1,"goodsCount":10,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"0.00","uniformSalePrice":"1000.00","salePrice":"1000.00","salePriceWithTax":"1001.00","taxRate":"0.01","money":"10010.00","goodsSumStockNum":390}],"goodsAllCount":10,"isCheckText":"已通过","isGatheringText":"待收款","isOutStoreText":"待出库","status":"作废中"}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<OrderSalesResponse> data;

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

    public List<OrderSalesResponse> getData() {
        return data;
    }

    public void setData(List<OrderSalesResponse> data) {
        this.data = data;
    }

    public static class OrderSalesResponse implements Serializable {
        /**
         * orderID : 15
         * companyID : 2
         * shopID : 1
         * orderType : 4
         * orderTime : 1587192681
         * outNum : wb001
         * orderNum : RWIJ_XS_00000011
         * isGathering : 0
         * isOutStore : 0
         * isCanceled : 0
         * salesPersonUserID : 1
         * salesPersonUserName : ewenXing
         * customerID : 1
         * customerName : 李四
         * realMoney : 10000.00
         * orderMakerDiscount : 100
         * orderMakerUserID : 1
         * orderMakerUserName : ewenXing
         * storeID : 1
         * storeName : 总仓
         * cancelType : 0
         * remark : 备注
         * isCheck : 0
         * checkTime : 0
         * checkNote :
         * checkUserID : 1
         * checkUserName : ewenXing
         * addTime : 1591323751
         * goodsInfo : [{"goodsID":1,"goodsCount":2,"goodsName":"MacBook Pro","goodsCatName":"Apple2","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","costPrice":"8888.00","uniformSalePrice":"500.00","salePrice":"500.00","salePriceWithTax":"500.00","taxRate":"0.01","money":"1000.00","goodsSumStockNum":390}]
         * goodsAllCount : 2
         * isCheckText : 待审核
         * isGatheringText : 待收款
         * isOutStoreText : 待出库
         * status : 未完成
         */

        private String orderID;
        private String companyID;
        private String shopID;
        private int orderType;
        private long orderTime;
        private String outNum;
        private String orderNum;
        private int isGathering;
        private int isOutStore;
        private int isCanceled;
        private String salesPersonUserID;
        private String salesPersonUserName;
        private String customerID;
        private String customerName;
        private String realMoney;
        private String orderMakerDiscount;
        private String orderMakerUserID;
        private String orderMakerUserName;
        private String storeID;
        private String storeName;
        private int cancelType;
        private String remark;
        private int isCheck;
        private long checkTime;
        private String checkNote;
        private String checkUserID;
        private String checkUserName;
        private long addTime;
        private int goodsAllCount;
        private String isCheckText;
        private String isGatheringText;
        private String isOutStoreText;
        private String status;

        //个人零售单
        private int storeType;

        //个人领货单
        private int takerUserID;
        private String takeUserName;

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

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public String getOutNum() {
            return outNum;
        }

        public void setOutNum(String outNum) {
            this.outNum = outNum;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public int getIsGathering() {
            return isGathering;
        }

        public void setIsGathering(int isGathering) {
            this.isGathering = isGathering;
        }

        public int getIsOutStore() {
            return isOutStore;
        }

        public void setIsOutStore(int isOutStore) {
            this.isOutStore = isOutStore;
        }

        public int getIsCanceled() {
            return isCanceled;
        }

        public void setIsCanceled(int isCanceled) {
            this.isCanceled = isCanceled;
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

        public String getCustomerID() {
            return customerID;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getRealMoney() {
            return realMoney;
        }

        public void setRealMoney(String realMoney) {
            this.realMoney = realMoney;
        }

        public String getOrderMakerDiscount() {
            return orderMakerDiscount;
        }

        public void setOrderMakerDiscount(String orderMakerDiscount) {
            this.orderMakerDiscount = orderMakerDiscount;
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

        public int getCancelType() {
            return cancelType;
        }

        public void setCancelType(int cancelType) {
            this.cancelType = cancelType;
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

        public String getIsGatheringText() {
            return isGatheringText;
        }

        public void setIsGatheringText(String isGatheringText) {
            this.isGatheringText = isGatheringText;
        }

        public String getIsOutStoreText() {
            return isOutStoreText;
        }

        public void setIsOutStoreText(String isOutStoreText) {
            this.isOutStoreText = isOutStoreText;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<GoodsInfoResponse> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public int getStoreType() {
            return storeType;
        }

        public void setStoreType(int storeType) {
            this.storeType = storeType;
        }

        public int getTakerUserID() {
            return takerUserID;
        }

        public void setTakerUserID(int takerUserID) {
            this.takerUserID = takerUserID;
        }

        public String getTakeUserName() {
            return takeUserName;
        }

        public void setTakeUserName(String takeUserName) {
            this.takeUserName = takeUserName;
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
