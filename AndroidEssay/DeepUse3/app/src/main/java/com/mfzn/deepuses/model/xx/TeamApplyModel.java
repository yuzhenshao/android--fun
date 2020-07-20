package com.mfzn.deepuses.model.xx;

import java.util.List;

public class TeamApplyModel {

    /**
     * total : 3
     * per_page : 10
     * current_page : 1
     * last_page : 1
     * data : [{"applyID":6,"companyID":13,"userID":2,"remark":"","type":2,"addtime":1569718440,"isPass":0,"checkTime":0,"workmateUID":0,"staffName":"","workmateUName":"","u_name":"791","u_head":"/uploads/u_head/20190115134258-7.jpg","u_phone":"15312550000","companyName":"啊啊啊啊1","from":"通过扫码加入"},{"applyID":4,"companyID":13,"userID":1,"remark":"通过一下2","type":3,"addtime":1569549543,"isPass":1,"checkTime":1569552591,"workmateUID":27,"staffName":"","workmateUName":"常州冲浪网络科技有限公司2","u_name":"ewen","u_head":"/public/images/u_head/1/20190505/2cff4ca7ae864420fda9ee9e3cdbb2a7.jpeg","u_phone":"15312555080","companyName":"啊啊啊啊1","from":"通过常州冲浪网络科技有限公司2邀请加入"},{"applyID":3,"companyID":13,"userID":28,"remark":"通过一下","type":3,"addtime":1569549082,"isPass":1,"checkTime":1569553452,"workmateUID":27,"staffName":"","workmateUName":"常州冲浪网络科技有限公司2","u_name":"市场专员","u_head":"","u_phone":"15300000011","companyName":"啊啊啊啊1","from":"通过常州冲浪网络科技有限公司2邀请加入"}]
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
         * applyID : 6
         * companyID : 13
         * userID : 2
         * remark :
         * type : 2
         * addtime : 1569718440
         * isPass : 0
         * checkTime : 0
         * workmateUID : 0
         * staffName :
         * workmateUName :
         * u_name : 791
         * u_head : /uploads/u_head/20190115134258-7.jpg
         * u_phone : 15312550000
         * companyName : 啊啊啊啊1
         * from : 通过扫码加入
         */

        private int applyID;
        private int companyID;
        private int userID;
        private String remark;
        private int type;
        private int addtime;
        private int isPass;
        private int checkTime;
        private int workmateUserID;
        private String staffName;
        private String workmateUserName;
        private String userName;
        private String userAvatar;
        private String userPhone;
        private String companyName;
        private String from;

        public int getApplyID() {
            return applyID;
        }

        public void setApplyID(int applyID) {
            this.applyID = applyID;
        }

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getIsPass() {
            return isPass;
        }

        public void setIsPass(int isPass) {
            this.isPass = isPass;
        }

        public int getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(int checkTime) {
            this.checkTime = checkTime;
        }


        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }



        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public int getWorkmateUserID() {
            return workmateUserID;
        }

        public void setWorkmateUserID(int workmateUserID) {
            this.workmateUserID = workmateUserID;
        }

        public String getWorkmateUserName() {
            return workmateUserName;
        }

        public void setWorkmateUserName(String workmateUserName) {
            this.workmateUserName = workmateUserName;
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

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }
    }
}
