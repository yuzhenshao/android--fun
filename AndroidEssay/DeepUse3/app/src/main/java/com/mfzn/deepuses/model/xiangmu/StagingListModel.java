package com.mfzn.deepuses.model.xiangmu;

import java.util.ArrayList;
import java.util.List;

public class StagingListModel {

    private List<EnginerBean> engineers;
    private List<EnginerBean> others;

    public List<EnginerBean> getEnginer() {
        return engineers;
    }

    public void setEnginer(List<EnginerBean> enginer) {
        this.engineers = enginer;
    }

    public List<EnginerBean> getOthers() {
        return others;
    }

    public void setOthers(List<EnginerBean> others) {
        this.others = others;
    }

    public static class EnginerBean {

        private int proID;
        private int userID;
        private int addTime;
        private String memberLabelID;
        private String userName;
        private String userAvatar;
        private String labelName;
        private int data_id;

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

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
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

        public String getMemberLabelID() {
            return memberLabelID;
        }

        public void setMemberLabelID(String memberLabelID) {
            this.memberLabelID = memberLabelID;
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
    }

    public List<EnginerBean> getAllEnginerList() {
        List<EnginerBean> enginerBeanList = new ArrayList<>();
        enginerBeanList.addAll(engineers);
        enginerBeanList.addAll(others);
        return enginerBeanList;
    }
}
