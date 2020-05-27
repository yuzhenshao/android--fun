package com.mfzn.deepuses.bean.response.store;

import java.io.Serializable;

public class StoreCheckGoodsResponse implements Serializable {

    /**
     * goodsID : 1
     * goodsCount : 0
     * goodsName : MacBook Pro
     * goodsCatName : Apple
     * goodsAttr : 最新的
     * goodsNum : SP0001
     * goodsUnitName : 台
     * goodsBarCode : 111111
     * goodsBrand : Apple
     * goodsPosition : 1
     * goodsMainImage : 1.jpg
     * systemStockNum : 100
     * checkStockNum : 99
     * checkChangeNum : -1
     * remark :
     */

    private String goodsID;
    private String goodsCount;
    private String goodsName;
    private String goodsCatName;
    private String goodsAttr;
    private String goodsNum;
    private String goodsUnitName;
    private String goodsBarCode;
    private String goodsBrand;
    private String goodsPosition;
    private String goodsMainImage;
    private int systemStockNum;
    private int checkStockNum;
    private int checkChangeNum;
    private String remark;

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
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

    public int getCheckChangeNum() {
        return checkChangeNum;
    }

    public void setCheckChangeNum(int checkChangeNum) {
        this.checkChangeNum = checkChangeNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
