package com.mfzn.deepuses.bean.response.shop;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class ShopListResponse implements Serializable {

    /**
     * shopID : 1
     * isMain : 1
     * shopNum : RWIJ_MD_000001
     * shopName : 总门店
     * chargePersonUserID : 1
     * userName : ewenXing
     * contactPhone :
     * shopAddress :
     * remark :
     * addTime : 1583744110
     */

    private String shopID;
    private int isMain;
    private String shopNum;
    private String shopName;
    private String chargePersonUserID;
    private String userName;
    private String contactPhone;
    private String shopAddress;
    private String remark;
    private long addTime;

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getChargePersonUserID() {
        return chargePersonUserID;
    }

    public void setChargePersonUserID(String chargePersonUserID) {
        this.chargePersonUserID = chargePersonUserID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
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

    @Override
    public int hashCode() {
        return TextUtils.isEmpty(getShopID()) ? 0 : getShopID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShopListResponse) {
            return !TextUtils.isEmpty(getShopID()) && getShopID().equals(((ShopListResponse) obj).getShopID());
        }
        return false;
    }
}
