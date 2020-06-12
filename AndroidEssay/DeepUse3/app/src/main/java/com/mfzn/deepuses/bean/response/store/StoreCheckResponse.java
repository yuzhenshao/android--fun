package com.mfzn.deepuses.bean.response.store;

import android.text.TextUtils;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bean.request.CommodityRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreCheckResponse implements Serializable {
    //0.待审核；1.盈亏处理中；2.审核被拒；3盈亏处理完成；4.无盈亏
    private final static int PENDING_REVIEW = 0;
    private final static int PROFIT_LOSS_ING = 1;
    private final static int PROFIT_LOSS_FAILED = 2;
    private final static int PROFIT_LOSS_SUCCEED = 3;
    private final static int NO_PROFIT_LOSS = 4;

    /**
     * orderID : 3
     * companyID : 2
     * shopID : 1
     * orderTime : 1586442493
     * orderMakerUserID : 1
     * orderMakerUserName : ewenXing
     * orderNum : RWIJ_PD_00000002
     * outNum : wb001
     * storeID : 1
     * storeName : 总仓
     * remark : 老鼠咬坏了一个
     * isCheck : 1
     * checkTime : 1586443698
     * checkUserID : 1
     * status : 4  状态：0.待审核；1.盈亏处理中；2.审核被拒；3盈亏处理完成；4.无盈亏
     * checkUserName : ewenXing
     * checkNote : 通过
     * addTime : 1586443678
     * statusText : 无盈亏
     * systemSumCount : 100
     * checkSumCount : 100
     */

    private String orderID;
    private String companyID;
    private String shopID;
    private long orderTime;
    private String orderMakerUserID;
    private String orderMakerUserName;
    private String orderNum;
    private String outNum;
    private String storeID;
    private String storeName;
    private String remark;
    private int isCheck;
    private long checkTime;
    private String checkUserID;
    private int status;
    private String checkUserName;
    private String checkNote;
    private long addTime;
    private String statusText;
    private int systemSumCount;
    private int checkSumCount;
    private List<StoreCheckGoodsResponse> goodsInfo;

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

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
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

    public String getCheckUserID() {
        return checkUserID;
    }

    public void setCheckUserID(String checkUserID) {
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

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getSystemSumCount() {
        return systemSumCount;
    }

    public void setSystemSumCount(int systemSumCount) {
        this.systemSumCount = systemSumCount;
    }

    public int getCheckSumCount() {
        return checkSumCount;
    }

    public void setCheckSumCount(int checkSumCount) {
        this.checkSumCount = checkSumCount;
    }

    public List<StoreCheckGoodsResponse> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<StoreCheckGoodsResponse> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public List<String> getGoodsMainImageList() {
        List<String> images = new ArrayList<>();
        if (!ListUtil.isEmpty(goodsInfo)) {
            for (StoreCheckGoodsResponse goodsResponse : goodsInfo) {
                if (!TextUtils.isEmpty(goodsResponse.getGoodsMainImage())) {
                    images.add(goodsResponse.getGoodsMainImage());
                }
            }
        }
        return images;
    }

    public int getStatusResId() {
        switch (status) {
            case PENDING_REVIEW:
                return R.mipmap.examine_pending;
            case PROFIT_LOSS_ING:
                return R.mipmap.icon_ongoing;
            case PROFIT_LOSS_FAILED:
                return R.mipmap.examine_unpass;
            case PROFIT_LOSS_SUCCEED:
                return R.mipmap.profit_loss_successed;
            case NO_PROFIT_LOSS:
                return R.mipmap.no_profit_loss;
        }
        return R.mipmap.examine_pending;
    }
}
