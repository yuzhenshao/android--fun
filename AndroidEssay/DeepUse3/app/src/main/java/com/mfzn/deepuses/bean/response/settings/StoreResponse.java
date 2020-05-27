package com.mfzn.deepuses.bean.response.settings;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.libcommon.tree.NodeId;

import java.io.Serializable;

/**
 * @author yz @date 2020-03-30
 */
public class StoreResponse implements Serializable {

    /**
     * storeID : 1
     * isMain : 1
     * storeNum : RWIJ_CK_000001
     * storeName : 总仓
     * chargePersonUserID : 1
     * userName : ewenXing
     * userAvatar :
     * contactPhone :
     * storeAddress :
     * remark:
     * addTime : 1583759887
     */

    private String storeID;
    private int isMain;
    private String storeNum;
    private String storeName;
    private String chargePersonUserID;
    private String userName;
    private String userAvatar;
    private String contactPhone;
    private String storeAddress;
    private String remark;
    private int addTime;

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
