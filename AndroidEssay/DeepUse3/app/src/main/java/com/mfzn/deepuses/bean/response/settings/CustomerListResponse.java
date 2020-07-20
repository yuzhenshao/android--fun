package com.mfzn.deepuses.bean.response.settings;

import com.mfzn.deepuses.R;

import java.io.Serializable;
import java.util.List;

public class CustomerListResponse {


    /**
     * total : 4
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"data_id":7,"userID":11,"userAvatar":"","companyID":2,"customerName":"马汉a","customerPhone":"15398761234","chargePersonName":"","chargePersonPhone":"","note":"哈哈哈","areaID":"","address":"","customerSourceID":1,"followStatusID":1,"addTime":1584112670,"customerType":1,"customerLevelID":1,"levelName":"一星级","salesPersonInfo":{"userID":1,"staffName":"哈哈哈"},"fullAddress":"无-无-无","fullCode":[0,0,0]},{"data_id":3,"userID":5,"userAvatar":"","companyID":2,"customerName":"张三","customerPhone":"15300000002","chargePersonName":"","chargePersonPhone":"","note":"","areaID":"","address":"","customerSourceID":0,"followStatusID":0,"addTime":1578409852,"customerType":1,"customerLevelID":0,"levelName":"","salesPersonInfo":{},"fullAddress":"无-无-无","fullCode":[0,0,0]},{"data_id":4,"userID":6,"userAvatar":"","companyID":2,"customerName":"李四四","customerPhone":"15300000003","chargePersonName":"","chargePersonPhone":"","note":"","areaID":"","address":"","customerSourceID":0,"followStatusID":0,"addTime":1578411476,"customerType":1,"customerLevelID":0,"levelName":"","salesPersonInfo":{},"fullAddress":"无-无-无","fullCode":[0,0,0]},{"data_id":5,"userID":7,"userAvatar":"","companyID":2,"customerName":"李五","customerPhone":"15300000004","chargePersonName":"","chargePersonPhone":"","note":"","areaID":"","address":"","customerSourceID":0,"followStatusID":0,"addTime":1578412663,"customerType":1,"customerLevelID":0,"levelName":"","salesPersonInfo":{},"fullAddress":"无-无-无","fullCode":[0,0,0]}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<CustomerResponse> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<CustomerResponse> getData() {
        return data;
    }

    public void setData(List<CustomerResponse> data) {
        this.data = data;
    }

    public static class CustomerResponse implements Serializable {
        /**
         * data_id : 7
         * userID : 11
         * userAvatar :
         * companyID : 2
         * customerName : 马汉a
         * customerPhone : 15398761234
         * chargePersonName :
         * chargePersonPhone :
         * note : 哈哈哈
         * areaID :
         * address :
         * customerSourceID : 1
         * followStatusID : 1
         * addTime : 1584112670
         * customerType : 1
         * customerLevelID : 1
         * levelName : 一星级
         * salesPersonInfo : {"userID":1,"staffName":"哈哈哈"}
         * fullAddress : 无-无-无
         * fullCode : [0,0,0]
         */

        private String data_id;
        private String userID;
        private String userAvatar;
        private String companyID;
        private String customerName;
        private String customerPhone;
        private String chargePersonName;
        private String chargePersonPhone;
        private String note;
        private String areaID;
        private String address;
        private String customerSourceID;
        private String followStatusID;
        private long addTime;
        private int customerType;//客户类型：1.普通客户；2.合作客户；
        private String customerLevelID;
        private String levelName;
        private SalesPersonInfoBean salesPersonInfo;
        private String fullAddress;
        private List<String> fullCode;

        public String getData_id() {
            return data_id;
        }

        public void setData_id(String data_id) {
            this.data_id = data_id;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getCompanyID() {
            return companyID;
        }

        public void setCompanyID(String companyID) {
            this.companyID = companyID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getChargePersonName() {
            return chargePersonName;
        }

        public void setChargePersonName(String chargePersonName) {
            this.chargePersonName = chargePersonName;
        }

        public String getChargePersonPhone() {
            return chargePersonPhone;
        }

        public void setChargePersonPhone(String chargePersonPhone) {
            this.chargePersonPhone = chargePersonPhone;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getAreaID() {
            return areaID;
        }

        public void setAreaID(String areaID) {
            this.areaID = areaID;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCustomerSourceID() {
            return customerSourceID;
        }

        public void setCustomerSourceID(String customerSourceID) {
            this.customerSourceID = customerSourceID;
        }

        public String getFollowStatusID() {
            return followStatusID;
        }

        public void setFollowStatusID(String followStatusID) {
            this.followStatusID = followStatusID;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getCustomerType() {
            return customerType;
        }

        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }

        public String getCustomerLevelID() {
            return customerLevelID;
        }

        public void setCustomerLevelID(String customerLevelID) {
            this.customerLevelID = customerLevelID;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public SalesPersonInfoBean getSalesPersonInfo() {
            return salesPersonInfo;
        }

        public void setSalesPersonInfo(SalesPersonInfoBean salesPersonInfo) {
            this.salesPersonInfo = salesPersonInfo;
        }

        public String getFullAddress() {
            return fullAddress;
        }

        public void setFullAddress(String fullAddress) {
            this.fullAddress = fullAddress;
        }

        public List<String> getFullCode() {
            return fullCode;
        }

        public void setFullCode(List<String> fullCode) {
            this.fullCode = fullCode;
        }

        public static class SalesPersonInfoBean implements Serializable {
            /**
             * userID : 1
             * staffName : 哈哈哈
             */

            private String userID;
            private String staffName;

            public String getUserID() {
                return userID;
            }

            public void setUserID(String userID) {
                this.userID = userID;
            }

            public String getStaffName() {
                return staffName;
            }

            public void setStaffName(String staffName) {
                this.staffName = staffName;
            }
        }

        public String getCustomerTypeName() {
            return customerType==1?"普通客户" : "合作客户";
        }

        public int getLevelIcon() {
            int levelID = Integer.parseInt((getCustomerLevelID()));
            switch (levelID) {
                case 1:
                    return R.mipmap.cus_level1;
                case 2:
                    return R.mipmap.cus_level2;
                case 3:
                    return R.mipmap.cus_level3;
                case 4:
                    return R.mipmap.cus_level4;
                case 5:
                    return R.mipmap.cus_level5;
            }
            return 0;
        }
    }


}
