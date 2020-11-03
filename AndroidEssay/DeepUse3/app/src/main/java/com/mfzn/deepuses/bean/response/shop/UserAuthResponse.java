package com.mfzn.deepuses.bean.response.shop;

import java.util.List;

public class UserAuthResponse {

    /**
     * normalAuth : [{"authID":1,"authModule":1,"authName":"销售","authDescription":"","pID":0,"isActive":0,"sons":[{"authID":2,"authModule":1,"authName":"销售管理","authDescription":"","pID":1,"isActive":0,"sons":[{"authID":3,"authModule":1,"authName":"","authDescription":"销售单据的操作","pID":2,"isActive":0,"sons":[]}]},{"authID":5,"authModule":1,"authName":"销售","authDescription":"","pID":1,"isActive":0,"sons":[{"authID":6,"authModule":1,"authName":"","authDescription":"销售单据的查看","pID":5,"isActive":0,"sons":[]}]}]},{"authID":7,"authModule":2,"authName":"采购","authDescription":"","pID":0,"isActive":0,"sons":[{"authID":8,"authModule":2,"authName":"采购管理","authDescription":"","pID":7,"isActive":0,"sons":[{"authID":9,"authModule":2,"authName":"","authDescription":"采购单据的操作","pID":8,"isActive":0,"sons":[]}]},{"authID":11,"authModule":2,"authName":"采购","authDescription":"","pID":7,"isActive":0,"sons":[{"authID":12,"authModule":2,"authName":"","authDescription":"采购单据的查看","pID":11,"isActive":0,"sons":[]}]}]},{"authID":13,"authModule":3,"authName":"财务","authDescription":"","pID":0,"isActive":0,"sons":[{"authID":14,"authModule":3,"authName":"财务管理","authDescription":"","pID":13,"isActive":0,"sons":[{"authID":15,"authModule":3,"authName":"","authDescription":"财务单据的操作","pID":14,"isActive":0,"sons":[]}]},{"authID":17,"authModule":3,"authName":"财务","authDescription":"","pID":13,"isActive":0,"sons":[{"authID":18,"authModule":3,"authName":"","authDescription":"财务单据的查看","pID":17,"isActive":0,"sons":[]}]}]},{"authID":26,"authModule":6,"authName":"其他","authDescription":"","pID":0,"isActive":0,"sons":[{"authID":27,"authModule":6,"authName":"商品管理","authDescription":"","pID":26,"isActive":0,"sons":[{"authID":28,"authModule":6,"authName":"","authDescription":"商品的增删改","pID":27,"isActive":0,"sons":[]}]},{"authID":29,"authModule":6,"authName":"客户管理","authDescription":"","pID":26,"isActive":0,"sons":[{"authID":30,"authModule":6,"authName":"","authDescription":"客户的增删改","pID":29,"isActive":0,"sons":[]}]},{"authID":31,"authModule":6,"authName":"供应商管理","authDescription":"","pID":26,"isActive":0,"sons":[{"authID":32,"authModule":6,"authName":"","authDescription":"供应商的增删改","pID":31,"isActive":0,"sons":[]}]},{"authID":33,"authModule":6,"authName":"门店员工管理","authDescription":"","pID":26,"isActive":0,"sons":[{"authID":34,"authModule":6,"authName":"","authDescription":"添加删除门店员工","pID":33,"isActive":0,"sons":[]}]}]}]
     * shopIDs : [{"shopID":1,"shopName":"总门店","isMain":1,"isActive":0},{"shopID":2,"shopName":"新北店","isMain":0,"isActive":0},{"shopID":4,"shopName":"武进店","isMain":0,"isActive":0}]
     * storeAuth : [{"storeID":1,"storeName":"总仓","storeNum":"RWIJ_CK_000001","isMain":1,"storeAuth":[{"authID":20,"authModule":4,"authName":"仓库管理","authDescription":"","pID":19,"storeID":1,"isActive":0,"sons":[{"authID":21,"authModule":4,"authName":"","authDescription":"仓库的基本操作","pID":20,"storeID":1,"isActive":0,"sons":[]}]},{"authID":23,"authModule":4,"authName":"仓库","authDescription":"","pID":19,"storeID":1,"isActive":0,"sons":[{"authID":24,"authModule":4,"authName":"","authDescription":"仓库的查看","pID":23,"storeID":1,"isActive":1,"sons":[]}]}]},{"storeID":2,"storeName":"分仓1","storeNum":"RWIJ_CK_000002","isMain":0,"storeAuth":[{"authID":20,"authModule":4,"authName":"仓库管理","authDescription":"","pID":19,"storeID":2,"isActive":0,"sons":[{"authID":21,"authModule":4,"authName":"","authDescription":"仓库的基本操作","pID":20,"storeID":2,"isActive":0,"sons":[]}]},{"authID":23,"authModule":4,"authName":"仓库","authDescription":"","pID":19,"storeID":2,"isActive":0,"sons":[{"authID":24,"authModule":4,"authName":"","authDescription":"仓库的查看","pID":23,"storeID":2,"isActive":0,"sons":[]}]}]},{"storeID":3,"storeName":"这个总仓","storeNum":"RWIJ_CK_000003","isMain":1,"storeAuth":[{"authID":20,"authModule":4,"authName":"仓库管理","authDescription":"","pID":19,"storeID":3,"isActive":0,"sons":[{"authID":21,"authModule":4,"authName":"","authDescription":"仓库的基本操作","pID":20,"storeID":3,"isActive":0,"sons":[]}]},{"authID":23,"authModule":4,"authName":"仓库","authDescription":"","pID":19,"storeID":3,"isActive":0,"sons":[{"authID":24,"authModule":4,"authName":"","authDescription":"仓库的查看","pID":23,"storeID":3,"isActive":0,"sons":[]}]}]},{"storeID":4,"storeName":"分仓2","storeNum":"RWIJ_CK_000004","isMain":0,"storeAuth":[{"authID":20,"authModule":4,"authName":"仓库管理","authDescription":"","pID":19,"storeID":4,"isActive":0,"sons":[{"authID":21,"authModule":4,"authName":"","authDescription":"仓库的基本操作","pID":20,"storeID":4,"isActive":0,"sons":[]}]},{"authID":23,"authModule":4,"authName":"仓库","authDescription":"","pID":19,"storeID":4,"isActive":0,"sons":[{"authID":24,"authModule":4,"authName":"","authDescription":"仓库的查看","pID":23,"storeID":4,"isActive":0,"sons":[]}]}]}]
     * discountStart : 1.0000
     */

    private String discountStart;
    private List<NormalAuthResponse> normalAuth;
    private List<ShopIDsResponse> shopIDs;
    private List<StoreAuthReponse> storeAuth;

    public String getDiscountStart() {
        return discountStart;
    }

    public void setDiscountStart(String discountStart) {
        this.discountStart = discountStart;
    }

    public List<NormalAuthResponse> getNormalAuth() {
        return normalAuth;
    }

    public void setNormalAuth(List<NormalAuthResponse> normalAuth) {
        this.normalAuth = normalAuth;
    }

    public List<ShopIDsResponse> getShopIDs() {
        return shopIDs;
    }

    public void setShopIDs(List<ShopIDsResponse> shopIDs) {
        this.shopIDs = shopIDs;
    }

    public List<StoreAuthReponse> getStoreAuth() {
        return storeAuth;
    }

    public void setStoreAuth(List<StoreAuthReponse> storeAuth) {
        this.storeAuth = storeAuth;
    }


    private class NormalAuthResponse {

        /**
         * authID : 1
         * authModule : 1
         * authName : 销售
         * authDescription :
         * pID : 0
         * isActive : 0
         * sons : [{"authID":2,"authModule":1,"authName":"销售管理","authDescription":"","pID":1,"isActive":0,"sons":[{"authID":3,"authModule":1,"authName":"","authDescription":"销售单据的操作","pID":2,"isActive":0,"sons":[]}]},{"authID":5,"authModule":1,"authName":"销售","authDescription":"","pID":1,"isActive":0,"sons":[{"authID":6,"authModule":1,"authName":"","authDescription":"销售单据的查看","pID":5,"isActive":0,"sons":[]}]}]
         */

        private String authID;
        private int authModule;
        private String authName;
        private String authDescription;
        private String pID;
        private int isActive;
        private List<AuthSons> sons;

        public String getAuthID() {
            return authID;
        }

        public void setAuthID(String authID) {
            this.authID = authID;
        }

        public int getAuthModule() {
            return authModule;
        }

        public void setAuthModule(int authModule) {
            this.authModule = authModule;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public String getAuthDescription() {
            return authDescription;
        }

        public void setAuthDescription(String authDescription) {
            this.authDescription = authDescription;
        }

        public String getPID() {
            return pID;
        }

        public void setPID(String pID) {
            this.pID = pID;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public List<AuthSons> getSons() {
            return sons;
        }

        public void setSons(List<AuthSons> sons) {
            this.sons = sons;
        }
    }

    private class ShopIDsResponse{

        /**
         * shopID : 1
         * shopName : 总门店
         * isMain : 1
         * isActive : 0
         */

        private String shopID;
        private String shopName;
        private int isMain;
        private int isActive;

        public String getShopID() {
            return shopID;
        }

        public void setShopID(String shopID) {
            this.shopID = shopID;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getIsMain() {
            return isMain;
        }

        public void setIsMain(int isMain) {
            this.isMain = isMain;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }
    }

    private class StoreAuthReponse{

        /**
         * storeID : 1
         * storeName : 总仓
         * storeNum : RWIJ_CK_000001
         * isMain : 1
         * storeAuth : []
         */

        private String storeID;
        private String storeName;
        private String storeNum;
        private int isMain;
        private List<AuthSons> storeAuth;

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

        public String getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(String storeNum) {
            this.storeNum = storeNum;
        }

        public int getIsMain() {
            return isMain;
        }

        public void setIsMain(int isMain) {
            this.isMain = isMain;
        }

        public List<AuthSons> getStoreAuth() {
            return storeAuth;
        }

        public void setStoreAuth(List<AuthSons> storeAuth) {
            this.storeAuth = storeAuth;
        }
    }
    class AuthSons{

        /**
         * authID : 24
         * authModule : 4
         * authName :
         * authDescription : 仓库的查看
         * pID : 23
         * storeID : 2
         * isActive : 0
         * sons : []
         */

        private String authID;
        private int authModule;
        private String authName;
        private String authDescription;
        private String pID;
        private String storeID;
        private int isActive;
        private List<AuthSons> sons;

        public String getAuthID() {
            return authID;
        }

        public void setAuthID(String authID) {
            this.authID = authID;
        }

        public int getAuthModule() {
            return authModule;
        }

        public void setAuthModule(int authModule) {
            this.authModule = authModule;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public String getAuthDescription() {
            return authDescription;
        }

        public void setAuthDescription(String authDescription) {
            this.authDescription = authDescription;
        }

        public String getPID() {
            return pID;
        }

        public void setPID(String pID) {
            this.pID = pID;
        }

        public String getStoreID() {
            return storeID;
        }

        public void setStoreID(String storeID) {
            this.storeID = storeID;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public List<?> getSons() {
            return sons;
        }

        public void setSons(List<AuthSons> sons) {
            this.sons = sons;
        }
    }


}
