package com.mfzn.deepuses.model.company;

import com.libcommon.utils.ListUtil;

import java.util.List;

public class SelectCompanyModel {

    /**
     * companyID : 1
     * companyName : 麦麸智能工程有限公司
     * createUserID : 312
     */

    private String companyID;
    private String companyName;
    private int createUserID;
    private boolean type = false;
    private String companyLogo;
    private int companyLevelID;
    private List<ShopResponse> shops;

    public String getLogo() {
        return companyLogo;
    }

    public void setLogo(String logo) {
        this.companyLogo = logo;
    }

    public int getCompanyLevel() {
        return companyLevelID;
    }

    public void setCompanyLevel(int companyLevel) {
        this.companyLevelID = companyLevel;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(int createUserID) {
        this.createUserID = createUserID;
    }

    public List<ShopResponse> getShops() {
        return shops;
    }

    public void setShops(List<ShopResponse> shops) {
        this.shops = shops;
    }


    public ShopResponse getMainShop(){
        if(!ListUtil.isEmpty(shops)){
            for(ShopResponse shopResponse:shops){
                if(shopResponse.isMainShop()){
                    return shopResponse;
                }
            }
        }
        return null;
    }

    public class ShopResponse {


        /**
         * shopID : 1
         * isMain : 1
         * shopNum : RWIJ_MD_000001
         * shopName : 总门店
         */

        private String shopID;
        private int isMain;
        private String shopNum;
        private String shopName;

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

        public boolean isMainShop(){
            return getIsMain()==1;
        }
    }
}
