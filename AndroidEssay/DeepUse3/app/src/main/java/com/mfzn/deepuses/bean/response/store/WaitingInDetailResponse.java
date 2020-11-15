package com.mfzn.deepuses.bean.response.store;

public class WaitingInDetailResponse {


    /**
     * detailID : 2
     * dataID : 2
     * goodsID : 1
     * goodsName : MacBook Pro
     * goodsNum : SP0001
     * goodsCatID : 4
     * catName : Apple
     * goodsAttr : 最新的
     * goodsBarCode : 111111
     * goodsMainImage : 1.jpg
     * goodsUnitID : 3
     * unitName : 台
     * shouldDone : 10
     * hasDone : 10
     * needDone : 0
     * status : 2
     * addTime : 1586141609
     * goodsSumStock : 390
     */

    private String detailID;
    private String dataID;
    private String goodsID;
    private String goodsName;
    private String goodsNum;
    private String goodsCatID;
    private String catName;
    private String goodsAttr;
    private String goodsBarCode;
    private String goodsMainImage;
    private String goodsUnitID;
    private String unitName;
    private int shouldDone;
    private int hasDone;
    private int needDone;
    private int status;
    private long addTime;
    private int goodsSumStock;
    private int size;

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

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

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
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

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    public String getGoodsMainImage() {
        return goodsMainImage;
    }

    public void setGoodsMainImage(String goodsMainImage) {
        this.goodsMainImage = goodsMainImage;
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

    public int getShouldDone() {
        return shouldDone;
    }

    public void setShouldDone(int shouldDone) {
        this.shouldDone = shouldDone;
    }

    public int getHasDone() {
        return hasDone;
    }

    public void setHasDone(int hasDone) {
        this.hasDone = hasDone;
    }

    public int getNeedDone() {
        return needDone;
    }

    public void setNeedDone(int needDone) {
        this.needDone = needDone;
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

    public int getGoodsSumStock() {
        return goodsSumStock;
    }

    public void setGoodsSumStock(int goodsSumStock) {
        this.goodsSumStock = goodsSumStock;
    }

    public int getSize() {
        return size==0?shouldDone:size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
