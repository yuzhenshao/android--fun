package com.mfzn.deepuses.bean.response.purchase;

import android.text.TextUtils;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.bean.response.settings.GoodsInfoResponse;
import com.mfzn.deepuses.purchasesellsave.sale.Module.OtherCostModule;

import java.util.ArrayList;
import java.util.List;

public class OrderPurchaseDetailResponse {


    /**
     * orderID : 1
     * companyID : 2
     * shopID : 1
     * orderType : 1
     * orderTime : 1586879276
     * orderNum : RWIJ_CG_00000001
     * outNum : wb001
     * isPay : 2
     * isInStore : 2
     * isGathering : 0
     * isOutStore : 0
     * supplierID : 1
     * supplierName : 供应商1
     * discountAmount : 100.00
     * realMoney : 10000.00
     * totalMoney : 10100.00
     * orderMakerUserID : 1
     * orderMakerUserName : ewenXing
     * isCanceled : 0
     * remark : 哈哈哈1
     * isCheck : 1
     * checkTime : 1586921311
     * checkNote : 通过
     * checkUserID : 1
     * checkUserName : ewenXing
     * addTime : 1586879361
     * storeID : 1
     * storeName : 总仓
     * goodsInfo : [{"goodsID":1,"goodsCount":100,"goodsName":"MacBook Pro","goodsCatName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"goodsMainImage":"1.jpg","buyPrice":"100.00","buyPriceWithTax":"101.00","taxRate":"0.01","money":"10100.00"}]
     * otherCost : [{"otherCostID":1,"otherCostTypeName":"运费","costMoney":"100.00","hasTax":0,"taxRate":"0.00"}]
     * goodsAllCount : 100
     * isCanceledText : 未作废
     * isCheckText : 已通过
     * isPayText : 已付款
     * isInStoreText : 已入库
     * isGatheringText : 待收款
     * isOutStoreText : 待出库
     */

    private String orderID;
    private String companyID;
    private String shopID;
    private int orderType;
    private long orderTime;
    private String orderNum;
    private String outNum;
    private int isPay;
    private int isInStore;
    private int isGathering;
    private int isOutStore;
    private String supplierID;
    private String supplierName;
    private String chargePerson;
    private String chargePersonPhone;
    private String discountAmount;
    private String realMoney;
    private String totalMoney;
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
    private String storeID;
    private String storeName;
    private int goodsAllCount;
    private String isCanceledText;
    private String isCheckText;
    private String isPayText;
    private String isInStoreText;
    private String isGatheringText;
    private String isOutStoreText;
    private List<GoodsInfoResponse> goodsInfo;
    private List<OtherCostModule> otherCost;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
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

    public int getGoodsAllCount() {
        return goodsAllCount;
    }

    public void setGoodsAllCount(int goodsAllCount) {
        this.goodsAllCount = goodsAllCount;
    }

    public String getIsCanceledText() {
        return isCanceledText;
    }

    public void setIsCanceledText(String isCanceledText) {
        this.isCanceledText = isCanceledText;
    }

    public String getIsCheckText() {
        return isCheckText;
    }

    public void setIsCheckText(String isCheckText) {
        this.isCheckText = isCheckText;
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

    public List<GoodsInfoResponse> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfoResponse> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public List<OtherCostModule> getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(List<OtherCostModule> otherCost) {
        this.otherCost = otherCost;
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

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getChargePersonPhone() {
        return chargePersonPhone;
    }

    public void setChargePersonPhone(String chargePersonPhone) {
        this.chargePersonPhone = chargePersonPhone;
    }
}
