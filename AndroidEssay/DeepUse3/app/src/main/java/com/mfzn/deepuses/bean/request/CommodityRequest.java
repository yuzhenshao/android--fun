package com.mfzn.deepuses.bean.request;

import android.text.TextUtils;

/**
 * @author yz @date 2020-03-26
 */
public class CommodityRequest {

    private String goodsID;
    private String goodsCatID;
    private String goodsName;
    private String catName;
    private String goodsAttr;
    private String goodsNum;
    private String unitName;
    private String goodsUnitID;
    private String goodsBrand;
    private String goodsPosition;
    private String goodsImages;
    private String goodsMainImage;
    private String goodsBarCode;
    private String costPrice;
    private String salePrice;
    private String otherShopWaringPrice;
    private String supplierIDs;
    private String storeStockNum;
    private int activeStoreWarning;
    private String storeWarningStockNum;
    private String remark;
    private long addTime;
    private int goodsSumStockNum;

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
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

    public String getGoodsBrand() {
        return goodsBrand;
    }


    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsPosition() {
        return goodsPosition;

    }

    public void setGoodsPosition(String goodsPosition) {
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

    public String getOtherShopWaringPrice() {
        return otherShopWaringPrice;
    }

    public void setOtherShopWaringPrice(String otherShopWaringPrice) {
        this.otherShopWaringPrice = otherShopWaringPrice;
    }

    public String getSupplierIDs() {
        return supplierIDs;
    }

    public void setSupplierIDs(String supplierIDs) {
        this.supplierIDs = supplierIDs;
    }

    public String getStoreStockNum() {
        return storeStockNum;
    }

    public void setStoreStockNum(String storeStockNum) {
        this.storeStockNum = storeStockNum;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    public String getStoreWarningStockNum() {
        return storeWarningStockNum;
    }

    public void setStoreWarningStockNum(String storeWarningStockNum) {
        this.storeWarningStockNum = storeWarningStockNum;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getGoodsSumStockNum() {
        return goodsSumStockNum;
    }

    public void setGoodsSumStockNum(int goodsSumStockNum) {
        this.goodsSumStockNum = goodsSumStockNum;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
