package com.mfzn.deepuses.bean.response.settings;

import android.net.Uri;
import android.text.TextUtils;

import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.purchasesellsave.store.model.GoodsImage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yz @date 2020-05-04
 */
public class GoodsDetailResponse implements Serializable {

    private GoodsInfoResponse goodsInfo;
    private List<StoresInfoResponse> storesInfo;
    private List<SalesRecordResponse> salesRecord;

    public GoodsInfoResponse getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfoResponse goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public List<StoresInfoResponse> getStoresInfo() {
        return storesInfo;
    }

    public void setStoresInfo(List<StoresInfoResponse> storesInfo) {
        this.storesInfo = storesInfo;
    }

    public List<SalesRecordResponse> getSalesRecord() {
        return salesRecord;
    }

    public void setSalesRecord(List<SalesRecordResponse> salesRecord) {
        this.salesRecord = salesRecord;
    }

    public static class GoodsInfoResponse implements Serializable {
        /**
         * goodsID : 1
         * companyID : 2
         * shopID : 1
         * goodsName : MacBook Pro
         * goodsCatID : 4
         * catName : Apple
         * goodsAttr : 最新的
         * goodsNum : SP0001
         * goodsUnitID : 3
         * unitName : 台
         * goodsBarCode : 111111
         * goodsBrand : Apple
         * goodsPosition : 1
         * costPrice : 8888.00
         * salePrice : 9999.00
         * activeStoreWarning : 1
         * goodsImages : 1.jpg,2.jpg
         * goodsMainImage : 1.jpg
         * remark : 哈www
         * addTime : 1586103699
         * goodsSumStockNum : 390
         * suppliers : [{"supplierID":1,"supplierName":"供应商1"}]
         * shopWaringPrice : [{"shopID":1,"shopName":"总门店","warningPrice":"10000.00"}]
         */

        private String goodsID;
        private String companyID;
        private String shopID;
        private String goodsName;
        private String goodsCatID;
        private String catName;
        private String goodsAttr;
        private String goodsNum;
        private String goodsUnitID;
        private String unitName;
        private String goodsBarCode;
        private String goodsBrand;
        private int goodsPosition;
        private String costPrice;
        private String salePrice;
        private int activeStoreWarning;
        private String goodsImages;
        private String goodsMainImage;
        private String remark;
        private int addTime;
        private int goodsSumStockNum;
        private List<StoreBean> goodsStoreStockNum;
        private List<SuppliersBean> suppliers;
        private List<ShopWaringPriceBean> shopWaringPrice;

        public String getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(String goodsID) {
            this.goodsID = goodsID;
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

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsCatID() {
            return goodsCatID;
        }

        public void setGoodsCatID(String goodsCatID) {
            this.goodsCatID = goodsCatID;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getGoodsAttr() {
            return goodsAttr;
        }

        public void setGoodsAttr(String goodsAttr) {
            this.goodsAttr = goodsAttr;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsUnitID() {
            return goodsUnitID;
        }

        public void setGoodsUnitID(String goodsUnitID) {
            this.goodsUnitID = goodsUnitID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getGoodsBarCode() {
            return goodsBarCode;
        }

        public void setGoodsBarCode(String goodsBarCode) {
            this.goodsBarCode = goodsBarCode;
        }

        public String getGoodsBrand() {
            return goodsBrand;
        }

        public void setGoodsBrand(String goodsBrand) {
            this.goodsBrand = goodsBrand;
        }

        public int getGoodsPosition() {
            return goodsPosition;
        }

        public void setGoodsPosition(int goodsPosition) {
            this.goodsPosition = goodsPosition;
        }

        public String getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(String costPrice) {
            this.costPrice = costPrice;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public int getActiveStoreWarning() {
            return activeStoreWarning;
        }

        public void setActiveStoreWarning(int activeStoreWarning) {
            this.activeStoreWarning = activeStoreWarning;
        }

        public String getGoodsImages() {
            return goodsImages;
        }

        public void setGoodsImages(String goodsImages) {
            this.goodsImages = goodsImages;
        }

        public String getGoodsMainImage() {
            return goodsMainImage;
        }

        public void setGoodsMainImage(String goodsMainImage) {
            this.goodsMainImage = goodsMainImage;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getGoodsSumStockNum() {
            return goodsSumStockNum;
        }

        public void setGoodsSumStockNum(int goodsSumStockNum) {
            this.goodsSumStockNum = goodsSumStockNum;
        }

        public List<SuppliersBean> getSuppliers() {
            return suppliers;
        }

        public void setSuppliers(List<SuppliersBean> suppliers) {
            this.suppliers = suppliers;
        }

        public List<ShopWaringPriceBean> getShopWaringPrice() {
            return shopWaringPrice;
        }

        public void setShopWaringPrice(List<ShopWaringPriceBean> shopWaringPrice) {
            this.shopWaringPrice = shopWaringPrice;
        }

        public String getPositionName() {
            return getGoodsPosition() == 1 ? "高端产品" : (getGoodsPosition() == 2 ? "中低端产品" : "暂无");
        }

        public List<StoreBean> getGoodsStoreStockNum() {
            return goodsStoreStockNum;
        }

        public void setGoodsStoreStockNum(List<StoreBean> goodsStoreStockNum) {
            this.goodsStoreStockNum = goodsStoreStockNum;
        }

        public static class StoreBean implements Serializable {

            private String storeID;
            private String storeName;
            private String stockNum;


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

            public String getStockNum() {
                return stockNum;
            }

            public void setStockNum(String stockNum) {
                this.stockNum = stockNum;
            }
        }

        public static class SuppliersBean implements Serializable {
            /**
             * supplierID : 1
             * supplierName : 供应商1
             */

            private String supplierID;
            private String supplierName;

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
        }

        public static class ShopWaringPriceBean implements Serializable {
            /**
             * shopID : 1
             * shopName : 总门店
             * warningPrice : 10000.00
             */

            private int shopID;
            private String shopName;
            private String warningPrice;

            public int getShopID() {
                return shopID;
            }

            public void setShopID(int shopID) {
                this.shopID = shopID;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getWarningPrice() {
                return warningPrice;
            }

            public void setWarningPrice(String warningPrice) {
                this.warningPrice = warningPrice;
            }
        }
    }

    public ArrayList<GoodsImage> getGoodsImgsUrl() {
        ArrayList<GoodsImage> urls = new ArrayList<>();
        String goodsImages = goodsInfo.getGoodsImages();
        if (!TextUtils.isEmpty(goodsImages)) {
            String[] files = goodsImages.split(",");
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (!TextUtils.isEmpty(files[i])) {
                        urls.add(new GoodsImage(ApiHelper.BASE_URL + files[i]));
                    }
                }
            } else {
                urls.add(new GoodsImage(ApiHelper.BASE_URL + goodsImages));
            }
        }
        return urls;
    }
}
