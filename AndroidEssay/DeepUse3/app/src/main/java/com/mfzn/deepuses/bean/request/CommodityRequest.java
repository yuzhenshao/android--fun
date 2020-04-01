package com.mfzn.deepuses.bean.request;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

/**
 * @author yz @date 2020-03-26
 */
public class CommodityRequest extends BaseObservable {

    private String goodsID;
    private String goodsCatID;
    private String goodsName;
    private String catName;
    private String goodsAttr;
    private String goodsNum;
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
    private int storeStockNum;
    private int activeStoreWarning;
    private String storeWarningStockNum;
    private String remark;
    private boolean isCanSubmit;

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    @Bindable
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsName);
        setCanSubmit(isRequiredParametersNotNull());
    }

    @Bindable
    public String getGoodsCatID() {
        return goodsCatID;
    }

    public void setGoodsCatID(String goodsCatID) {
        this.goodsCatID = goodsCatID;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsCatID);
    }


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Bindable
    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsAttr);
    }

    @Bindable
    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsNum);
        setCanSubmit(isRequiredParametersNotNull());
    }

    @Bindable
    public String getGoodsUnitID() {
        return goodsUnitID;
    }

    public void setGoodsUnitID(String goodsUnitID) {
        this.goodsUnitID = goodsUnitID;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsUnitID);
    }

    @Bindable
    public String getGoodsBrand() {
        return goodsBrand;
    }


    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsBrand);
    }

    @Bindable
    public String getGoodsPosition() {
        return goodsPosition;

    }

    public void setGoodsPosition(String goodsPosition) {
        this.goodsPosition = goodsPosition;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsPosition);
    }

    @Bindable
    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
        notifyPropertyChanged(com.mfzn.deepuses.BR.costPrice);
        setCanSubmit(isRequiredParametersNotNull());
    }

    @Bindable
    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
        notifyPropertyChanged(com.mfzn.deepuses.BR.costPrice);
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

    @Bindable
    public String getSupplierIDs() {
        return supplierIDs;
    }

    public void setSupplierIDs(String supplierIDs) {
        this.supplierIDs = supplierIDs;
        notifyPropertyChanged(com.mfzn.deepuses.BR.costPrice);
    }

    public int getStoreStockNum() {
        return storeStockNum;
    }

    public void setStoreStockNum(int storeStockNum) {
        this.storeStockNum = storeStockNum;
    }

    @Bindable
    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
        notifyPropertyChanged(com.mfzn.deepuses.BR.goodsBarCode);
    }

    public String getStoreWarningStockNum() {
        return storeWarningStockNum;
    }

    public void setStoreWarningStockNum(String storeWarningStockNum) {
        this.storeWarningStockNum = storeWarningStockNum;
    }

    @Bindable
    public boolean isCanSubmit() {
        return isCanSubmit;
    }

    public void setCanSubmit(boolean canSubmit) {
        this.isCanSubmit = canSubmit;
        notifyPropertyChanged(com.mfzn.deepuses.BR.canSubmit);
    }

    private boolean isRequiredParametersNotNull() {
        return !TextUtils.isEmpty(goodsName) &&
                !TextUtils.isEmpty(goodsNum) &&
                !TextUtils.isEmpty(goodsBrand) &&
                !TextUtils.isEmpty(goodsCatID) &&
                !TextUtils.isEmpty(goodsAttr) &&
                !TextUtils.isEmpty(goodsUnitID) &&
                !TextUtils.isEmpty(goodsPosition);
    }
}
