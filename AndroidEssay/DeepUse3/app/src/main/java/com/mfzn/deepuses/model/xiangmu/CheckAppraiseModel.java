package com.mfzn.deepuses.model.xiangmu;

import java.util.List;

public class CheckAppraiseModel {

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
     * fileUrl : ["/uploads/a0001/调试方案/20190312143706-10_首页 下拉.png,","/uploads/a0001/实景效果/20190312143715-04_登录4.png,"]
     * data_id : 1
     * data_en_id : FahBBddd4
     */

    private String orderNo;
    private int userId;
    private int addTime;
    private String stars;
    private String content;
    private String fileId;
    private int is_del;
    private int orderNum;
    private UserInfoBean userInfo;
    private int data_id;
    private String data_en_id;
    private List<String> fileUrl;

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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
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

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }

    public static class UserInfoBean {
        /**
         * u_name : 王玛尼
         * u_head : /uploads/u_head//20191012113649-1280x800_MarioKart8Deluxe.jpg
         */

        private String u_name;
        private String u_head;

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
    }
}
