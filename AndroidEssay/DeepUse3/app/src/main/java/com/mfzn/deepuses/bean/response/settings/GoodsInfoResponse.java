package com.mfzn.deepuses.bean.response.settings;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsInfoResponse implements Serializable {

    private String goodsID;
    private int goodsCount=1;
    private String goodsName;
    private String goodsCatID;
    @SerializedName("catName")
    private String goodsCatName;
    private String goodsAttr;
    private String goodsNum;

    private String goodsUnitID;
    @SerializedName("unitName")
    private String goodsUnitName;
    private String goodsBarCode;
    private String goodsBrand;
    private String goodsPosition;
    private String goodsMainImage;
    private String uniformSalePrice;
    private String salePrice;
    private String salePriceWithTax;
    private String costPrice;
    private double taxRate;
    private boolean isHasTaxRate;
    private String money;

    private String goodsImages;
    private String otherShopWaringPrice;
    private String supplierIDs;
    private String storeStockNum;
    private int activeStoreWarning;
    private String storeWarningStockNum;
    private String remark;
    private long addTime;
    private int goodsSumStockNum;
    private int systemStockNum;
    private int checkStockNum;

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCatName() {
        return goodsCatName;
    }

    public void setGoodsCatName(String goodsCatName) {
        this.goodsCatName = goodsCatName;
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

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
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

    public String getGoodsPosition() {
        return goodsPosition;
    }

    public void setGoodsPosition(String goodsPosition) {
        this.goodsPosition = goodsPosition;
    }

    public String getGoodsMainImage() {
        return goodsMainImage;
    }

    public void setGoodsMainImage(String goodsMainImage) {
        this.goodsMainImage = goodsMainImage;
    }

    public String getUniformSalePrice() {
        return uniformSalePrice;
    }

    public void setUniformSalePrice(String uniformSalePrice) {
        this.uniformSalePrice = uniformSalePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSalePriceWithTax() {
        if (TextUtils.isEmpty(salePriceWithTax)) {
            return salePrice;
        }
        return salePriceWithTax;
    }

    public void setSalePriceWithTax(String salePriceWithTax) {
        this.salePriceWithTax = salePriceWithTax;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGoodsCatID() {
        return goodsCatID;
    }

    public void setGoodsCatID(String goodsCatID) {
        this.goodsCatID = goodsCatID;
    }

    public String getGoodsUnitID() {
        return goodsUnitID;
    }

    public void setGoodsUnitID(String goodsUnitID) {
        this.goodsUnitID = goodsUnitID;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
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

    public int getActiveStoreWarning() {
        return activeStoreWarning;
    }

    public void setActiveStoreWarning(int activeStoreWarning) {
        this.activeStoreWarning = activeStoreWarning;
    }

    public String getStoreWarningStockNum() {
        return storeWarningStockNum;
    }

    public void setStoreWarningStockNum(String storeWarningStockNum) {
        this.storeWarningStockNum = storeWarningStockNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public boolean isHasTaxRate() {
        return isHasTaxRate;
    }

    public void setHasTaxRate(boolean hasTaxRate) {
        isHasTaxRate = hasTaxRate;
    }

    @Override
    public int hashCode() {
        return TextUtils.isEmpty(getGoodsID()) ? 0 : getGoodsID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GoodsInfoResponse) {
            return !TextUtils.isEmpty(getGoodsID()) && getGoodsID().equals(((GoodsInfoResponse) obj).getGoodsID());
        }
        return false;
    }

    public int getSystemStockNum() {
        return systemStockNum;
    }

    public void setSystemStockNum(int systemStockNum) {
        this.systemStockNum = systemStockNum;
    }

    public int getCheckStockNum() {
        return checkStockNum;
    }

    public void setCheckStockNum(int checkStockNum) {
        this.checkStockNum = checkStockNum;
    }
}
