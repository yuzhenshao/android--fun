package com.mfzn.deepuses.bean.response.shop;

import java.io.Serializable;
import java.util.List;

public class ShopUserListResponse {

    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"dataID":1,"companyID":2,"shopID":1,"userID":1,"userPhone":"15312555080","userAvatar":"1.jpg","staffName":"哈哈哈","departmentID":1,"departmentName":"总裁办","addTime":1586508508}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<ShopUserResponse> data;

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

    public List<ShopUserResponse> getData() {
        return data;
    }

    public void setData(List<ShopUserResponse> data) {
        this.data = data;
    }

    public static class ShopUserResponse implements Serializable {
        /**
         * dataID : 1
         * companyID : 2
         * shopID : 1
         * userID : 1
         * userPhone : 15312555080
         * userAvatar : 1.jpg
         * staffName : 哈哈哈
         * departmentID : 1
         * departmentName : 总裁办
         * addTime : 1586508508
         */

        private String dataID;
        private String companyID;
        private String shopID;
        private String userID;
        private String userPhone;
        private String userAvatar;
        private String staffName;
        private String departmentID;
        private String departmentName;
        private long addTime;

        public String getDataID() {
            return dataID;
        }

        public void setDataID(String dataID) {
            this.dataID = dataID;
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

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getDepartmentID() {
            return departmentID;
        }

        public void setDepartmentID(String departmentID) {
            this.departmentID = departmentID;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
