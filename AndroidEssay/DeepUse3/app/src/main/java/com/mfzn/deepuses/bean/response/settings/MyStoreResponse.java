package com.mfzn.deepuses.bean.response.settings;

public class MyStoreResponse {


    /**
     * storeID : 1
     * storeName : 总仓
     * storeType : 1
     */

    private String storeID;
    private String storeName;
    private int storeType;

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

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }
}
