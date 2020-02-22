package com.mfzn.deepuses.model.xiangmu;

import java.util.List;

public class ProjectNewsModel {

    /**
     * total : 1
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"proID":977,"userID":28,"remark":"哈哈哈","is_del":0,"addTime":1573632005,"checkUserID":0,"checkTime":0,"checkRemark":"","label":"1","checkstatus":0,"isPass":0,"u_name":"我是好人","u_head":"/uploads/u_head/28/20191107/ef4449888b2d98af4006f1e879bbf3b3.jpg","pro_name":"ios-11-12","labelName":"售后客服","data_id":4,"data_en_id":"FadBXa1QP"}]
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

    public static class DataBean {
        /**
         * proID : 977
         * userID : 28
         * remark : 哈哈哈
         * is_del : 0
         * addTime : 1573632005
         * checkUserID : 0
         * checkTime : 0
         * checkRemark :
         * label : 1
         * checkstatus : 0
         * isPass : 0
         * u_name : 我是好人
         * u_head : /uploads/u_head/28/20191107/ef4449888b2d98af4006f1e879bbf3b3.jpg
         * pro_name : ios-11-12
         * labelName : 售后客服
         * data_id : 4
         * data_en_id : FadBXa1QP
         */

        private int proID;
        private int userID;
        private String remark;
        private int is_del;
        private int addTime;
        private int checkUserID;
        private int checkTime;
        private String checkRemark;
        private String label;
        private int checkstatus;
        private int isPass;
        private String u_name;
        private String u_head;
        private String pro_name;
        private String labelName;
        private int data_id;
        private String data_en_id;

        public int getProID() {
            return proID;
        }

        public void setProID(int proID) {
            this.proID = proID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getCheckUserID() {
            return checkUserID;
        }

        public void setCheckUserID(int checkUserID) {
            this.checkUserID = checkUserID;
        }

        public int getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(int checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckRemark() {
            return checkRemark;
        }

        public void setCheckRemark(String checkRemark) {
            this.checkRemark = checkRemark;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getCheckstatus() {
            return checkstatus;
        }

        public void setCheckstatus(int checkstatus) {
            this.checkstatus = checkstatus;
        }

        public int getIsPass() {
            return isPass;
        }

        public void setIsPass(int isPass) {
            this.isPass = isPass;
        }

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getU_head() {
            return u_head;
        }

        public void setU_head(String u_head) {
            this.u_head = u_head;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
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
