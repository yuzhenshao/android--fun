package com.mfzn.deepuses.bean.request.setting;

public class SetUserAuthRequest {
    private String normalAuth;
    private String shopIDs;
    private String storeAuth;
    private String userID;
    private String companyID;
    private String discountStart;

    public String getNormalAuth() {
        return normalAuth;
    }

    public void setNormalAuth(String normalAuth) {
        this.normalAuth = normalAuth;
    }

    public String getShopIDs() {
        return shopIDs;
    }

    public void setShopIDs(String shopIDs) {
        this.shopIDs = shopIDs;
    }

    public String getStoreAuth() {
        return storeAuth;
    }

    public void setStoreAuth(String storeAuth) {
        this.storeAuth = storeAuth;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getDiscountStart() {
        return discountStart;
    }

    public void setDiscountStart(String discountStart) {
        this.discountStart = discountStart;
    }
}
