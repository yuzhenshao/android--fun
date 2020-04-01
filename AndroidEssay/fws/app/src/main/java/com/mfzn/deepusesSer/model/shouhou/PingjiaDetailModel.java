package com.mfzn.deepusesSer.model.shouhou;

import java.util.List;

public class PingjiaDetailModel {


    /**
     * orderNo : yzs2017082600001
     * userId : 312
     * addTime : 1572855066
     * stars : 5
     * content :
     * fileId : 10,9
     * is_del : 0
     * orderNum : 0
     * userInfo : {"u_name":"王玛尼","u_head":"/uploads/u_head//20191012113649-1280x800_MarioKart8Deluxe.jpg"}
     * fileUrl : ["/uploads/a0001/调试方案/20190312143706-10_首页 下拉.png,"]
     * data_id : 1
     * data_en_id : FahBBddd4
     */

    private String orderNo;
    private int userId;
    private int addTime;
    private String stars;
    private String content;
    private int isDel;
    private int orderNum;
    private UserInfoBean userInfo;
    private int data_id;
    private String data_en_id;
    private List<String> fileUrls;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
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

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public static class UserInfoBean {
        /**
         * u_name : 王玛尼
         * u_head : /uploads/u_head//20191012113649-1280x800_MarioKart8Deluxe.jpg
         */

        private String userName;
        private String userAvatar;

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
}
