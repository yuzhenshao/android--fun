package com.mfzn.deepuses.model.khgl;

import java.util.List;

public class WholeCustomerModel {

    /**
     * total : 4
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"uid":4,"companyID":1,"u_name":"张三1","note":"这个是备注","is_del":0,"addtime":"0","updateUserID":0,"updateTime":"0","proID":1,"relatedProIDs":"2","followStatusID":0,"customerLevelID":0,"customerSourceID":0,"customerPhone":"15300000000","levelName":"","hasSalesperson":0,"pros":[{"proID":1,"pro_name":"项目名称1"},{"proID":2,"pro_name":"龙湖2"}],"data_id":1,"data_en_id":"Fh1SQdvX4"},{"uid":3,"companyID":1,"u_name":"李四","note":"备注一下","is_del":0,"addtime":"0","updateUserID":0,"updateTime":"0","proID":2,"relatedProIDs":"3","followStatusID":0,"customerLevelID":0,"customerSourceID":0,"customerPhone":"15311111111","levelName":"","hasSalesperson":0,"pros":[{"proID":2,"pro_name":"龙湖2"},{"proID":3,"pro_name":"龙湖3"}],"data_id":2,"data_en_id":"Fv1aSfhXk"},{"uid":31,"companyID":1,"u_name":"张三","note":"哈哈哈","is_del":0,"addtime":"1577078605","updateUserID":0,"updateTime":"0","proID":1,"relatedProIDs":"2,3","followStatusID":1,"customerLevelID":4,"customerSourceID":1,"customerPhone":"15312345678","levelName":"一星级","hasSalesperson":1,"pros":[{"proID":1,"pro_name":"项目名称1"},{"proID":2,"pro_name":"龙湖2"},{"proID":3,"pro_name":"龙湖3"}],"data_id":3,"data_en_id":"FvaBSBX1U"},{"uid":32,"companyID":1,"u_name":"李四","note":"哈哈哈","is_del":0,"addtime":"1577078695","updateUserID":0,"updateTime":"0","proID":3,"relatedProIDs":"1,2","followStatusID":2,"customerLevelID":5,"customerSourceID":2,"customerPhone":"15312345679","levelName":"二星级","hasSalesperson":1,"pros":[{"proID":1,"pro_name":"项目名称1"},{"proID":2,"pro_name":"龙湖2"},{"proID":3,"pro_name":"龙湖3"}],"data_id":4,"data_en_id":"FadBXa1QP"}]
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
         * uid : 4
         * companyID : 1
         * u_name : 张三1
         * note : 这个是备注
         * is_del : 0
         * addtime : 0
         * updateUserID : 0
         * updateTime : 0
         * proID : 1
         * relatedProIDs : 2
         * followStatusID : 0
         * customerLevelID : 0
         * customerSourceID : 0
         * customerPhone : 15300000000
         * levelName :
         * hasSalesperson : 0
         * pros : [{"proID":1,"pro_name":"项目名称1"},{"proID":2,"pro_name":"龙湖2"}]
         * data_id : 1
         * data_en_id : Fh1SQdvX4
         */

        private int uid;
        private int companyID;
        private String u_name;
        private String note;
        private int is_del;
        private String addtime;
        private int updateUserID;
        private String updateTime;
        private int proID;
        private String relatedProIDs;
        private int followStatusID;
        private int customerLevelID;
        private int customerSourceID;
        private String customerPhone;
        private String levelName;
        private int hasSalesperson;
        private int data_id;
        private String data_en_id;
        private List<ProsBean> pros;
        private boolean selectType = false;

        public boolean getSelectType() {
            return selectType;
        }

        public void setSelectType(boolean selectType) {
            this.selectType = selectType;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getCompanyID() {
            return companyID;
        }

        public void setCompanyID(int companyID) {
            this.companyID = companyID;
        }

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getUpdateUserID() {
            return updateUserID;
        }

        public void setUpdateUserID(int updateUserID) {
            this.updateUserID = updateUserID;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getProID() {
            return proID;
        }

        public void setProID(int proID) {
            this.proID = proID;
        }

        public String getRelatedProIDs() {
            return relatedProIDs;
        }

        public void setRelatedProIDs(String relatedProIDs) {
            this.relatedProIDs = relatedProIDs;
        }

        public int getFollowStatusID() {
            return followStatusID;
        }

        public void setFollowStatusID(int followStatusID) {
            this.followStatusID = followStatusID;
        }

        public int getCustomerLevelID() {
            return customerLevelID;
        }

        public void setCustomerLevelID(int customerLevelID) {
            this.customerLevelID = customerLevelID;
        }

        public int getCustomerSourceID() {
            return customerSourceID;
        }

        public void setCustomerSourceID(int customerSourceID) {
            this.customerSourceID = customerSourceID;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public int getHasSalesperson() {
            return hasSalesperson;
        }

        public void setHasSalesperson(int hasSalesperson) {
            this.hasSalesperson = hasSalesperson;
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

        public List<ProsBean> getPros() {
            return pros;
        }

        public void setPros(List<ProsBean> pros) {
            this.pros = pros;
        }

        public static class ProsBean {
            /**
             * proID : 1
             * pro_name : 项目名称1
             */

            private int proID;
            private String pro_name;

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public String getPro_name() {
                return pro_name;
            }

            public void setPro_name(String pro_name) {
                this.pro_name = pro_name;
            }
        }
    }
}
