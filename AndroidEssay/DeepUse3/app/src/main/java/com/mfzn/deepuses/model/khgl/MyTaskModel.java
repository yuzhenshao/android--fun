package com.mfzn.deepuses.model.khgl;

import java.io.Serializable;
import java.util.List;

public class MyTaskModel implements Serializable {

    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"companyID":1,"userID":2,"customerID":1,"taskTime":1577190000,"noticeTime":1,"remark":"哈哈哈","isDel":0,"addTime":1577167697,"updateTime":0,"customerName":"李四","customerPhone":"15312555080","data_id":1,"data_en_id":"Fh1SQdvX4"}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * companyID : 1
         * userID : 2
         * customerID : 1
         * taskTime : 1577190000
         * noticeTime : 1
         * remark : 哈哈哈
         * isDel : 0
         * addTime : 1577167697
         * updateTime : 0
         * customerName : 李四
         * customerPhone : 15312555080
         * data_id : 1
         * data_en_id : Fh1SQdvX4
         */

        private int companyID;
        private int userID;
        private int customerID;
        private int taskTime;
        private int noticeTime;
        private String remark;
        private int isDel;
        private int addTime;
        private int updateTime;
        private String customerName;
        private String customerPhone;
        private int data_id;
        private String data_en_id;

        public int getCompanyID() {
            return companyID;
        }

        public void setCompanyID(int companyID) {
            this.companyID = companyID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getCustomerID() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID = customerID;
        }

        public int getTaskTime() {
            return taskTime;
        }

        public void setTaskTime(int taskTime) {
            this.taskTime = taskTime;
        }

        public int getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(int noticeTime) {
            this.noticeTime = noticeTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
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

        public int getData_id() {
            return data_id;
        }

        public void setData_id(int data_id) {
            this.data_id = data_id;
        }

        public String getData_en_id() {
            return data_en_id;
        }

        public void setData_en_id(String data_en_id) {
            this.data_en_id = data_en_id;
        }
    }
}
